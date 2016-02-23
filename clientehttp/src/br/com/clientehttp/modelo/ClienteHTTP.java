/**
 * 
 */
package br.com.clientehttp.modelo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;


/**
 * @author weryquessantos
 *
 */
public class ClienteHTTP {
	/** A URL é recebida como parametro */
	public String enviarGet(String urlBruta) throws UnknownHostException, IOException{
		/** A URL é convertida para o tipo URL */
		URL url = new URL(urlBruta);

		/** Pegamos apenas o host da url, por exemplo se for https://www.rfc-editor.org/rfc/rfc2616.txt
		* a variável enderecoServidor recebe www.rfc-editor.org */
		String enderecoServidor = url.getHost();
	  
		/** Pegamos apenas o diretorio do aquivo. Considerando o exemplo acima
		* a varável arquivo conterá apena /rfc/rfc2616.txt */
		String arquivo = url.getFile();	 
	    
		/** Criamos e socket e fazemos a conexão no host pela porta 80. 
		 * Isso é como se fosse um telnet www.rfc-editor.org 80.*/
		Socket clienteSocketTCP = new Socket(enderecoServidor, 80);
		
		/** A conexão foi feita. */
	    System.out.println("Conectado em: "+ clienteSocketTCP.getInetAddress() +"\nPorta: "+ clienteSocketTCP.getPort() +"\nProtocolo: "+ url.getProtocol());

	    /** Todo socket tem por padrão 2 metodos de escrita e leitura de Streams 
	     * (forma como os dados transmitidos pela rede são tratados),
	     * o getOutputStream() e o getInputStream, respectivamente. */
	    
	    /** Aqui criamos um PrintStream (quem implementa o println), 
	     * a partir do metodo de escrita do socket, para facilitar o envio da requisição.*/
		PrintStream envia = new PrintStream(clienteSocketTCP.getOutputStream());
		
		/** Aqui criamos um BufferedReader para leitura "Bufferizada" 
		 * (lê todo um bloco de uma vez, armazena e passa os bytes um a um para o usuário).*/
		BufferedReader input = new BufferedReader(new InputStreamReader(clienteSocketTCP.getInputStream()));
		
		System.out.println("ClienteHTTP iniciada.");
		/** Escrevemos o cabeçalho da requisição. 
		 * (Como se estivessemos escrevendo no terminal ao usar o telnet): */
		/** Adicionando a versão 1.1 do HTTP, garantimos a conexão persistente. */
		envia.println("GET "+ arquivo +" HTTP/1.1");
		envia.println("Host: "+ enderecoServidor);
		envia.println("Connection: Close");
		envia.println();
		/** Envia a requisição. */
	    System.out.println("Requisição feita.");
	    
	    /** Criamos um construtor, para formatarmos a resposta recebida. */
	    StringBuilder construtor = new StringBuilder();
	    String userInput;
//	    int i = 1;
	    
	    System.out.println("Resposta recebida: ");
	    while ((userInput = input.readLine()) != null) {
	    	/** Uma linha é adicionada*/
	        construtor.append(userInput);
	        /** Adiciona um quebra de linha, para começar a adicionar a proxima linha lida em baixo*/
	        construtor.append("\n");
//	        System.out.println("Linha "+ i +": " + input.readLine());
//	        i++;
	    }
	    
	    /** A conexão é fechada.*/
	    clienteSocketTCP.close();
	    System.out.println("Socket cliente fechado.");
	    
	    /** Retorno a resposta construida, para ser exibida*/
	    return construtor.toString();
	  }
}
