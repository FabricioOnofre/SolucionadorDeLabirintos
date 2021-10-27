public class Coordenada
{
	private int coordenadaY = 0;
	private int coordenadaX = 0;
	
	public Coordenada(int linha, int coluna)
	{
		this.coordenadaX = coluna;
		this.coordenadaY = linha;
	}	
	
	/*---------------------------------------------------------------*/
	
	public int getLinha ()
	{
		return this.coordenadaY;
	}
	
	public int getColuna()
	{
		return this.coordenadaX;
	}	
	
	
	/*---------------------------------------------------------------*/
	
	public void setLinha (int novaLinha)
	{
		this.coordenadaY = novaLinha;
	}
	
	public void setColuna(int novaColuna)
	{
		this.coordenadaX = novaColuna;
	}	
	
	
	/*---------------------------------------------------------------*/
	

    @Override
    public String toString ()
    {
		String ret;
		
		ret  = "(";	
		ret +=	this.coordenadaY;	
		ret += ",";		
		ret +=	this.coordenadaX;			
		ret += ")";		
		
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

        Coordenada compara = (Coordenada) obj;
            
		if(this.coordenadaX != compara.coordenadaX)
			return false;		
			
		if(this.coordenadaY != compara.coordenadaY)
			return false;					

        return true;
    }
    
    /*---------------------------------------------------------------*/
  
    @Override
    public int hashCode ()
    {        		
		int ret = 1;
		
		ret = 7  * ret + new Integer (this.coordenadaX).hashCode();
		ret = 13 * ret + new Integer (this.coordenadaY).hashCode();

		       
		if (ret < 0) ret =-ret;
		    
	    return ret;    
    }
    
    /*---------------------------------------------------------------*/

	public Coordenada (Coordenada modelo) throws Exception
	{
		if (modelo==null)
			throw new Exception ("Modelo ausente");
			

		this.coordenadaX    = modelo.coordenadaX;
		this.coordenadaY    = modelo.coordenadaY;				
  	}
  	
  	/*---------------------------------------------------------------*/
  	
  	public Object clone ()
  	{
		Coordenada ret = null;
		
		try
		{
			ret = new Coordenada(this);
		}
		catch (Exception erro)
        {}
        
        return ret;
	}
	
	/*---------------------------------------------------------------*/
}
