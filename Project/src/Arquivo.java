import java.io.*;


public class Arquivo
{
			
	private BufferedReader ler; 
	private FileReader     arq;
		
	private String         nomeArq;
	private String[]       conteudo;
	private String         arqLido;
				
	private int           qtdLinhas;
	private int           qtdColunas;
	private boolean       linhasErrado;
	private boolean       colunasErrado;
		
	
	
	public Arquivo(String arq)  throws Exception
	{		
		this.nomeArq = arq;
		this.arq = new FileReader(arq);
		this.ler = new BufferedReader(this.arq);
				
		lerArquivo();
		ler.close();
	}		

	
	private void lerArquivo() throws Exception
	{
		
		boolean segundaLinha  = true;
		boolean primeiralinha = true;
		
		String teste = "";  

		String linhaLida = ler.readLine();		
		
		if(linhaLida == null) 
            throw new Exception ("Arquivo vazio!");
        else    
			this.qtdLinhas = Integer.parseInt(linhaLida);
			
		String[] linhas = new String[qtdLinhas];
		
		int tamanhoColuna = 0;
		
		while (linhaLida != null) 
		{
			if(segundaLinha || primeiralinha)
			{				
				if(primeiralinha == false)	// significa que está na segunda
				{										
					this.qtdColunas = Integer.parseInt(linhaLida); // Na segunda linha do arquivo fica a quantidade de colunas	
					segundaLinha = false;
				}					
				primeiralinha = false;
				linhaLida = ler.readLine();	
			}
			else
			{
				if(linhaLida.length() != qtdColunas)	
				{
					//throw new Exception ("Tamanho das linhas ERRADO!");
					linhasErrado = true;
				}
					
				linhas[tamanhoColuna] = linhaLida;
				tamanhoColuna++;
				
				teste += linhaLida;
				teste += "\n";
				linhaLida = ler.readLine();
				
			}			
		}
		

		if(tamanhoColuna != this.qtdLinhas)
		{
			//throw new Exception ("Tamanho das colunas ERRADO!");
			colunasErrado = true;
		}

		conteudo = linhas;
		arqLido = teste;
				
	}		
	
	public String lerLinha(int numLinha) throws Exception
	{		
		String ret = "";
		ret =  conteudo[numLinha-1];
		return ret;			
	}
	

	public int getQtdLinhas()
	{
		return this.qtdLinhas;
	}
	
	public boolean getErroLinhas()
	{
		return this.linhasErrado;
	}
	
	public boolean getErroColunas()
	{
		return this.colunasErrado;
	} 
	
	public int getQtdColunas()
	{
		return this.qtdColunas;
	}
	
	public String arquivoLido() throws Exception
	{				           
		return arqLido;			
	}
	
	public void FecharArquivo() throws Exception
	{
		ler.close();
	}		
	
	
	/*---------------------------------------------------------------*/
    
    @Override
    public String toString ()
    {		
		return arqLido;
	}
	
	
	/*---------------------------------------------------------------*/
	
	@Override
    public boolean equals (Object obj)  
    {
        if(this==obj)
            return true;

        if(obj==null)
            return false;

        if(this.getClass() != obj.getClass())
            return false;

        Arquivo compara = (Arquivo) obj;
            
		if(this.nomeArq != compara.nomeArq)
			return false;		
			
		if(this.ler != compara.ler)
			return false;	
						
		if(this.arq != compara.arq) 
			return false;									
											
        return true;
    }
    
    /*---------------------------------------------------------------*/
  
    @Override
    public int hashCode ()
    {        		
		int ret = 1;
		
		ret = 7  * ret + this.ler.hashCode();
		ret = 13 * ret + this.nomeArq.hashCode();
		ret = 11 * ret + this.arq.hashCode();

		       
		if (ret < 0) ret =-ret;
		    
	    return ret;    
    }
    
    /*---------------------------------------------------------------*/

	public Arquivo (Arquivo modelo) throws Exception
	{
		if (modelo==null)
			throw new Exception ("Modelo ausente");
			

		this.ler       = modelo.ler;
		this.nomeArq   = modelo.nomeArq;
		this.arq       = modelo.arq;
				
  	}
  	
  	/*---------------------------------------------------------------*/
  	
  	public Object clone ()
  	{
		Arquivo ret = null;
		
		try
		{
			ret = new Arquivo(this);
		}
		catch (Exception erro)
        {}
        
        return ret;
	}
	
	/*---------------------------------------------------------------*/
}
