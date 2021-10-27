import java.lang.reflect.*;

public class Matriz implements Cloneable
{
	
	private int qtdLinhas  = 0;
	private int qtdColunas = 0;
	private String[][] matriz;
	
	private Coordenada entrada;
		
	// Possiveis erros do LABIRINTO
	private boolean faltaParedes;
	private boolean faltaEntrada;
	private boolean faltaSaida;
	private boolean maisSaidas;
	private boolean maisEntradas;
	private boolean linhasErrado;
	private boolean colunasErrado;


	public Matriz(int linha, int coluna)
	{
		this.qtdColunas = coluna;
		this.qtdLinhas  = linha;
		this.matriz     = new String [qtdLinhas][qtdColunas];
	}	
	
	public Matriz(Arquivo labirinto) throws Exception
	{
		
		this.qtdColunas    = labirinto.getQtdColunas ();
		this.qtdLinhas     = labirinto.getQtdLinhas  ();
		this.linhasErrado  = labirinto.getErroLinhas ();
		this.colunasErrado = labirinto.getErroColunas();
		
		this.matriz = new String [qtdLinhas][qtdColunas];
		
		String lerMatriz = "";
		
		byte qtdEntrada = 0;
		byte qtdSaida   = 0;

		for (int linha = 0; linha < qtdLinhas; linha++ ) 
		{								
			lerMatriz = labirinto.lerLinha(linha+1);		
								
			for (int coluna = 0; coluna < qtdColunas; coluna++)
			{				
				String posicao     = lerMatriz.substring(coluna, coluna+1); // abcd
				
				if(linha == 0 || linha == qtdLinhas-1 || coluna == 0 || coluna == qtdColunas-1)		
					if(posicao.equals(" "))
					{
						//throw new Exception ("Labirinto faltando PAREDES!");	
						this.faltaParedes = true;
					}		
							
					 

				if(posicao.equals("E"))
				{
					if(qtdEntrada < 1)
					{
						entrada = new Coordenada(linha, coluna);
						qtdEntrada++;
					}
					else	
					{				
						//throw new Exception ("Labirinto com mais de uma ENTRADA!");		
						this.maisEntradas = true;
					}	
				}		
				else if(posicao.equals("S"))
				{
					if(qtdSaida < 1)
						qtdSaida++;
					else
					{
						//throw new Exception ("Labirinto com mais de uma SAIDA!");
						this.maisSaidas = true;
					}
				}	                                                                                                                                                     
				setPosicao(linha,coluna,posicao);
			}			
		}
		
		
		if(qtdEntrada < 1)
		{			
			this.faltaEntrada = true;
			throw new Exception ("Labirinto sem ENTRADA!");
		}
		else if (qtdSaida < 1)
		{			
			this.faltaSaida  = true;
			//throw new Exception ("Labirinto sem SAIDA!");
		}
	}	
	
	
	
	public void setPosicao(int linha, int coluna, String valor)
	{
		matriz[linha][coluna] = valor;
	}
	
	
	public String getPosicao (int linha, int coluna)
    {
		String  ret =  matriz[linha][coluna];
			
		return ret;
	}	
	
	
	public boolean getColunasErrado ()
    {
		return this.colunasErrado;
	}	
		
	public boolean getLinhasErrado ()
    {
		return this.linhasErrado;

	}	
	
	public boolean getFaltaParedes ()
    {
		return this.faltaParedes;
	}	
			
	public boolean getFaltaEntrada ()
    {
		return this.faltaEntrada;
	}	
	
	public boolean getFaltaSaida ()
    {
		return this.faltaSaida;
	}		
	
	public boolean getMaisEntradas ()
    {
		return this.maisEntradas;
	}	
	
	public boolean getMaisSaidas ()
    {
		return this.maisSaidas;
	}	
		
	public Coordenada getEntrada ()
    {			
		return entrada;
	}
	
	
	
	/*---------------------------------------------------------------*/
    
    @Override
    public String toString ()
    {
		String ret = "";
		
		for (int linha = 0; linha < qtdLinhas; linha++) 
		{			
			for (int coluna =0; coluna < qtdColunas; coluna++)
			{				
				ret += matriz[linha][coluna];
			}
			ret += "\n";						
		}
		
		return ret;
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

        Matriz compara = (Matriz) obj;
            
		if(this.qtdLinhas != compara.qtdLinhas)
			return false;		
			
		if(this.qtdColunas != compara.qtdColunas)
			return false;	
					
			
		for (int linha = 0; linha < qtdLinhas; linha++) 
		{			
			for (int coluna =0; coluna < qtdColunas; coluna++)
			{		
				if(!( this.matriz[linha][coluna].equals(compara.matriz[linha][coluna])))		
					return false;
			}
		}
						
        return true;
    }
    
    /*---------------------------------------------------------------*/
  
    @Override
    public int hashCode ()
    {        		
		int ret = 1;
		
		ret = 7  * ret + new Integer (this.qtdLinhas).hashCode();
		ret = 13 * ret + new Integer (this.qtdColunas).hashCode();

		for (int linha = 0; linha < qtdLinhas; linha++) 
		{			
			for (int coluna =0; coluna < qtdColunas; coluna++)
			{		
				ret = 5 * ret + ((this.matriz[linha][coluna]).hashCode());
			}
		}
		       
		if (ret < 0) ret =-ret;
		    
	    return ret;    
    }
    
    /*---------------------------------------------------------------*/

	public Matriz (Matriz modelo) throws Exception
	{
		if (modelo==null)
			throw new Exception ("Modelo ausente");			

		this.qtdLinhas    = modelo.qtdLinhas;
		this.qtdColunas   = modelo.qtdColunas;
		
		for (int linha = 0; linha < qtdLinhas; linha++) 
		{			
			for (int coluna =0; coluna < qtdColunas; coluna++)
			{		
				this.matriz[linha][coluna] = modelo.matriz[linha][coluna];
			}
		}				
  	}
  	
  	/*---------------------------------------------------------------*/
  	
  	public Object clone ()
  	{
		Matriz ret = null;
		
		try
		{
			ret = new Matriz(this);
		}
		catch (Exception erro)
        {}
        
        return ret;
	}
	
	/*---------------------------------------------------------------*/
}
