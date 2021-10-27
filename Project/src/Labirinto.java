public class Labirinto 
{
	
	//Objetos	
	private Matriz                  labirinto;
	private Pilha<Coordenada>       caminhoErrado;	
		
	private Coordenada 	            atual;					
	private Pilha<Coordenada>       caminho;		
	private Pilha<Fila<Coordenada>> possibilidades;			
	private Pilha<Coordenada>       inverso;
	private Fila<Coordenada>        fila;
	
	// Caracteristicas do LABIRINTO
	private int     linha;       // Linha  atual
	private int     coluna;	     // Coluna atual
	private int     qtdLinhas;   // Quantidade de linhas
	private int     qtdColunas;  // Quantidade de colunas
	private int     tamanhoPil;  // Area do Labirinto
	
	
	// Possiveis erros do LABIRINTO
	private boolean faltaParedes, faltaEntrada, faltaSaida;
	private boolean maisSaidas,   maisEntradas;
	private boolean linhasErrado, colunasErrado;
	

	
	public Labirinto(Matriz labirinto,  int qtdColunas, int qtdLinhas)  throws Exception
	{					
				
		this.labirinto      = labirinto;
		this.faltaParedes   = labirinto.getFaltaParedes ();
		this.faltaEntrada   = labirinto.getFaltaEntrada ();
		this.faltaSaida     = labirinto.getFaltaSaida   ();
		this.maisSaidas     = labirinto.getMaisSaidas   ();
		this.maisEntradas   = labirinto.getMaisEntradas ();
		this.linhasErrado   = labirinto.getLinhasErrado ();
		this.colunasErrado  = labirinto.getColunasErrado();

		this.linha          = labirinto.getEntrada().getLinha();
		this.coluna         = labirinto.getEntrada().getColuna();	
		this.qtdColunas     = qtdColunas;
		this.qtdLinhas      = qtdLinhas;
		this.tamanhoPil     = qtdColunas * qtdLinhas;
	
		this.atual          = new Coordenada             (linha,coluna );						
		//this.atual        = labirinto.getEntrada();						
		this.caminho        = new Pilha<Coordenada>      (tamanhoPil   );		
		this.caminhoErrado  = new Pilha<Coordenada>      (tamanhoPil   );
		this.possibilidades = new Pilha<Fila<Coordenada>>(tamanhoPil   );	
		this.inverso        = new Pilha<Coordenada>      (tamanhoPil   );
		this.fila           = new Fila<Coordenada>       (3);	
			
	}	
	

	/*---------------------------------------------------------------*/
		
	public void tratadorDeErro() throws Exception
	{
		if(faltaParedes)
			throw new Exception("Labirinto faltando PAREDES!");		
			
		if(faltaEntrada)
			throw new Exception("Labirinto sem ENTRADA!");		
			
		if(faltaSaida)
			throw new Exception("Labirinto sem SAIDA!");		
			
		if(maisEntradas)
			throw new Exception("Labirinto com mais de uma ENTRADA!");		
			
		if(maisSaidas)
			throw new Exception("Labirinto com mais de uma SAIDA!");		
			
		if(colunasErrado)
			throw new Exception("Tamanho das colunas ERRADO!");		
			
		if(linhasErrado)
			throw new Exception("Tamanho das linhas ERRADO!");
	}
	
	/*---------------------------------------------------------------*/
			
	private boolean temCaminho(String pos, String valor)
    {
		if(!(pos.equals(valor)))
		{						
			if(pos.equals(" ") || pos.equals("S"))
			{							
				return true;
			}
		}								
							
		return false;
	}
	
	/*---------------------------------------------------------------*/
		
	private boolean temCaminho(String poss, Coordenada pos, Coordenada valor)
    {
		
		if(!(pos.equals(valor)))
		{						
			if(poss.equals(" ") || poss.equals("S"))
			{							
				return true;
			}
		}								
		
					
		return false;
	}
	
	/*---------------------------------------------------------------*/
	
	public void acharSaida() throws Exception
	{
				
		String possivelCaminho = "#";
		String valor = "#";

		Coordenada coordenada;
		Coordenada possicaoErrada = null;
		
		
		forever:for(;;)
		{				
			this.fila = new Fila<Coordenada>(3);

			linha  = atual.getLinha();
			coluna = atual.getColuna();
			
						
			if(!caminhoErrado.isVazia())
			{
				possicaoErrada = caminhoErrado.recupereUmItem();
				valor = labirinto.getPosicao(possicaoErrada.getLinha(), possicaoErrada.getColuna());
			}
			

			// Verifica a possibilidade de ir para Baixo
			if(!(linha == qtdLinhas -1))
			{			
				possivelCaminho = labirinto.getPosicao(linha+1, coluna);				
							
				boolean pode = temCaminho(possivelCaminho,  valor);
									
				if(pode)
				{
					Coordenada possivel1 = new Coordenada(linha+1,coluna);
					fila.guardeUmItem(possivel1);						
				}				
			}

			// Verifica a possibilidade de ir para Cima
			if(!(linha == 0))
			{
										
				possivelCaminho = labirinto.getPosicao(linha-1, coluna);

				boolean pode = temCaminho(possivelCaminho, valor);
									
				if(pode)
				{										
					Coordenada possivel2 = new Coordenada(linha-1,coluna);
					fila.guardeUmItem(possivel2);

				}							
			}
			

			// Verifica a possibilidade de ir para Direita					
			if(!(coluna == qtdColunas -1 ))
			{
				possivelCaminho = labirinto.getPosicao(linha, coluna+1);
				coordenada = new Coordenada(linha, coluna+1);	
									
				boolean pode = temCaminho(possivelCaminho, coordenada, possicaoErrada);
				
				if(pode)
				{														
					Coordenada possivel3 = new Coordenada(linha,coluna+1);
					fila.guardeUmItem(possivel3);						  
				}		
			}
			
			// Verifica a possibilidade de ir para Esquerda								
			if(!(coluna == 0))
			{
				possivelCaminho = labirinto.getPosicao(linha, coluna-1);
				coordenada = new Coordenada(linha, coluna-1);	

				
				boolean pode = temCaminho(possivelCaminho, coordenada, possicaoErrada);
				
				if(pode)
				{		
					Coordenada possivel4 = new Coordenada(linha,coluna-1);
					fila.guardeUmItem(possivel4);
				}
			}
			
			
			if(fila.isVazia())
			{
				possibilidades.guardeUmItem(fila);		

				if(!caminho.isVazia())
				{

					boolean passoAtras = true;
																								
					while(fila.isVazia())
					{		
						if(caminho.isVazia())
							throw new Exception("Nao existe caminho ate a SAIDA");	
																							
						atual = caminho.recupereUmItem();
						caminho.removaUmItem();
							
						linha  = atual.getLinha();
						coluna = atual.getColuna();			
												
						fila = possibilidades.recupereUmItem();
						possibilidades.removaUmItem();
						
						if(!fila.isVazia())
						{
							labirinto.setPosicao(linha, coluna, "*");													
							caminho.guardeUmItem(atual);
						}
						else
							labirinto.setPosicao(linha, coluna, " ");
							
						if(fila.isVazia() && !passoAtras)
							caminhoErrado.removaUmItem();
							
						passoAtras = false;

					}												
				}
				else
					break forever;												
					
			}
			else
			{							
				atual = fila.recupereUmItem();							
				fila.removaUmItem();


				linha  = atual.getLinha();
				coluna = atual.getColuna();						

				if(labirinto.getPosicao(linha, coluna).equals("S"))
				{
					System.out.print("\nLABIRINTO SOLUCIONADO! \n\n");						
								
					System.out.print(labirinto);
						
					System.out.print("\n\n\n\n");						

																		
					while(!caminho.isVazia())
					{
						inverso.guardeUmItem(caminho.recupereUmItem());
						caminho.removaUmItem();
					}
					break forever;
				}
				else
				{										
					
					labirinto.setPosicao(linha, coluna, "*");						
					caminho.guardeUmItem(atual);
					caminhoErrado.guardeUmItem(atual);
					possibilidades.guardeUmItem(fila);		
					
				}
			}				
		}
											
				
		if(caminho.isVazia())
		{
			System.out.println("Coordenadas da ENTRADA ate a SAIDA: \n");
			
			for(int i = 1; !inverso.isVazia(); i++)
			{
				System.out.print(inverso.recupereUmItem());
				if(i%10==0)
					System.out.println("\n");
				inverso.removaUmItem();
			}
			System.out.println("\n\nFIM \n");
		}
		else
			System.out.println("IMPOSSIVEL ACHAR SAIDA");
	}		
	
	/*---------------------------------------------------------------*/
    
    @Override
    public String toString ()
    {
		return labirinto.toString();		
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

        Labirinto compara = (Labirinto) obj;
            
		if(this.atual.equals(compara.atual))
            return false;
            
        if(this.labirinto.equals(compara.labirinto))
            return false; 
            
        if(this.caminho.equals(compara.caminho))
            return false; 
            
        if(this.caminhoErrado.equals(compara.caminhoErrado))
            return false; 
            
        if(this.possibilidades.equals(compara.possibilidades))
            return false; 
            
        if(this.inverso.equals(compara.inverso))
            return false; 
            
        if(this.fila.equals(compara.fila))
            return false;                          
            
        if(this.linha != compara.linha)
            return false; 
            
        if(this.coluna != compara.coluna)
            return false; 
            
        if(this.qtdLinhas != compara.qtdLinhas)
            return false; 
            
        if(this.qtdColunas != compara.qtdColunas)
            return false; 
            
        if(this.tamanhoPil != compara.tamanhoPil)
            return false; 
                                     
        
        if(this.faltaParedes  != compara.faltaParedes)
            return false;
            
        if(this.faltaEntrada !=  compara.faltaEntrada)
            return false; 
            
        if(this.faltaSaida != compara.faltaSaida)
            return false; 
            
        if(this.maisSaidas != compara.maisSaidas)
            return false; 
            
        if(this.maisEntradas != compara.maisEntradas)
            return false; 
            
        if(this.linhasErrado != compara.linhasErrado)
            return false; 
            
        if(this.colunasErrado != compara.colunasErrado)
            return false;
       
            
        return true;
    }
    
    /*---------------------------------------------------------------*/
      
    @Override
    public int hashCode ()
    {        		
		int ret = 1;
		
		ret = 7  * ret +  (this.labirinto).hashCode();
		ret = 13 * ret +  (this.caminho).hashCode();		
		ret = 7  * ret +  (this.atual).hashCode();
		ret = 13 * ret +  (this.caminhoErrado).hashCode();		
		ret = 7  * ret +  (this.possibilidades).hashCode();
		ret = 13 * ret +  (this.inverso).hashCode();		
		ret = 7  * ret +  (this.fila).hashCode();
		
		
		ret = 13 * ret + new Integer (this.linha).hashCode();		
		ret = 7  * ret + new Integer (this.coluna).hashCode();
		ret = 13 * ret + new Integer (this.qtdLinhas).hashCode();		
		ret = 7  * ret + new Integer (this.qtdColunas).hashCode();
		ret = 13 * ret + new Integer (this.tamanhoPil).hashCode();	
		
			
		ret = 7  * ret + new Boolean (this.faltaEntrada).hashCode();
		ret = 13 * ret + new Boolean (this.faltaParedes).hashCode();		
		ret = 7  * ret + new Boolean (this.faltaSaida).hashCode();
		ret = 13 * ret + new Boolean (this.maisEntradas).hashCode();		
		ret = 7  * ret + new Boolean (this.maisSaidas).hashCode();
		ret = 13 * ret + new Boolean (this.linhasErrado).hashCode();		
		ret = 7  * ret + new Boolean (this.colunasErrado).hashCode();

		       
		if (ret < 0) ret =-ret;
		    
	    return ret;    
    }
    
    /*---------------------------------------------------------------*/
    
    
    public int compareTo (Labirinto lab)
    {
        if (this.tamanhoPil < lab.tamanhoPil) return -1; // menor
        if (this.tamanhoPil > lab.tamanhoPil) return  1; // maior

        return 0; //igual
    }
    
    /*---------------------------------------------------------------*/

	public Labirinto (Labirinto modelo) throws Exception
	{
		if (modelo==null)
			throw new Exception ("Modelo ausente");
			

		this.labirinto      = modelo.labirinto;
		this.atual          = modelo.atual;			
		this.caminho        = modelo.caminho;
		this.caminhoErrado  = modelo.caminhoErrado;		
		this.possibilidades = modelo.possibilidades;
		this.fila           = modelo.fila;			
		this.inverso        = modelo.inverso;

		this.linha          = modelo.linha;
		this.coluna         = modelo.coluna;
		this.qtdColunas     = modelo.qtdColunas;
		this.qtdLinhas      = modelo.qtdLinhas;
		this.tamanhoPil     = modelo.tamanhoPil;
		
		
		this.faltaEntrada   = modelo.faltaEntrada;
		this.faltaParedes   = modelo.faltaParedes;
		this.faltaSaida     = modelo.faltaSaida ;
		this.maisEntradas   = modelo.maisEntradas;
		this.maisSaidas     = modelo.maisSaidas;
		this.linhasErrado   = modelo.linhasErrado;
		this.colunasErrado  = modelo.colunasErrado;
		
  	}
  	
  	/*---------------------------------------------------------------*/
  	
  	public Object clone ()
  	{
		Labirinto ret = null;
		
		try
		{
			ret = new Labirinto(this);
		}
		catch (Exception erro)
        {}
        
        return ret;
	}
	
	/*---------------------------------------------------------------*/
  
}
