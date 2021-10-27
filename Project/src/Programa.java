import java.io.*;

public class Programa
{
	public static void main (String [] args)
	{
		try
		{
			
			System.out.println("Nome do arquivo que contem o Labirinto:" + "\n");
			String endereco = Teclado.getUmString();
			
			
			Arquivo   arq       = new Arquivo(endereco);
			Matriz    matriz    = new Matriz(arq);
			Labirinto labirinto = new Labirinto(matriz, arq.getQtdColunas(), arq.getQtdLinhas());
						
			labirinto.tratadorDeErro();
			labirinto.acharSaida();			

			
		}
		catch(Exception erro)
		{
			System.err.println(erro);
		}
	}
}
