package Controle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

public class Jogo extends JFrame implements EtapasLoop, KeyListener{
	private LoopPrincipal loop = new LoopPrincipal(this, 60);
    
    private long previous = System.currentTimeMillis();
	private Sapo sapo;
	private Veiculo veiculos[];
	private Boolean veiculoNoMapa[];
	private Boolean pista1[];
	private Boolean pista2[];
	private Boolean pista3[];
	private final int TAM_PISTA = 13;
	private final int NRO_VEICULOS = 10;
	private Mapa mapa;
	
	public Jogo() {
		super("Frogger");
		this.addKeyListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 378, 357);
		setResizable(false);
		getContentPane().setLayout(null);
		setIgnoreRepaint(true);
		setFocusable(true);
		sapo = new Sapo();
		addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e) {
                //Se apertar o x, paramos o loop.
                loop.stop();
            }
        });
		
	}
	
	public void iniciarLoop() {
		new Thread(loop, "Main loop").start();
	}
	public Sapo getSapo() {
		return sapo;
	}

	@Override
	public void setup(int velocidade) {
		// TODO Auto-generated method stub
		//Criamos a estratégia de double buffering
	    createBufferStrategy(2);
	    Random random = new Random();
	    veiculos = new Veiculo[NRO_VEICULOS];
	    for (int i = 0; i < NRO_VEICULOS; i++) {
	    	if (i< NRO_VEICULOS/4)
	    		veiculos[i] = new Onibus(velocidade);
	    	else if (i >= NRO_VEICULOS/4 && i < NRO_VEICULOS/2)
	    		veiculos[i] = new Carro(velocidade);
	    	else if (i >= NRO_VEICULOS/2 && i < NRO_VEICULOS)
	    		veiculos[i] = new Moto(velocidade);
	    }
	    for (int i = 0; i < NRO_VEICULOS; i++) {
	    	int j = random.nextInt(veiculos.length);
	    	
	    	Veiculo temp = veiculos[i];
			veiculos[i] = veiculos[j];
			veiculos[j] = temp;
	    }
	    pista1 = new Boolean[TAM_PISTA];
	    pista2 = new Boolean[TAM_PISTA];
	    pista3 = new Boolean[TAM_PISTA];
	    veiculoNoMapa = new Boolean[NRO_VEICULOS];
	    for (int i = 0; i < TAM_PISTA; i++) {
	    	pista1[i] = false;
	    	pista2[i] = false;
	    	pista3[i] = false;
	    }
	    for (int i = 0; i < NRO_VEICULOS; i++) {
	    	veiculoNoMapa[i] = false;
	    }
		mapa = new Mapa();
		sapo.ResetaPosicao();
	}

	@Override
	public void processLogics() {
		// TODO Auto-generated method stub
		//Calcula o tempo entre dois updates
	    long time = System.currentTimeMillis() - previous;
	    if (sapo.getVidas() == 0)
	    	System.exit(0);
	    if (sapo.getYmatriz() == 0)
	    	loop.nextLevel();
	    for (int i = 0;i < TAM_PISTA; i++ ) {
	    	pista1[i] = false;
	    	pista2[i] = false;
	    	pista3[i] = false;
	    }
	    for (int i = 0; i < NRO_VEICULOS; i++) {
	    	if (veiculoNoMapa[i]) {
	    		if(veiculos[i].getVisible()) {
		    		for (int j= veiculos[i].getXmatriz(); j< veiculos[i].getNroBlocosOcupa() + veiculos[i].getXmatriz(); j++) {
		    			if(sapo.getYmatriz() == veiculos[i].getYmatriz() && sapo.getXmatriz() == j) {
		    				sapo.ResetaPosicao();
		    				sapo.PerdeVida();
		    			}
		    		}
		    		veiculos[i].update(time);
		    		switch (veiculos[i].getPista()) {
		    			case 1:
		    				for (int j = veiculos[i].getXmatriz(); j < veiculos[i].getXmatriz() + veiculos[i].getNroBlocosOcupa(); j++)
		    					pista1[j + 3] = true;
		    				break;
		    			case 2:
		    				for (int j = veiculos[i].getXmatriz(); j < veiculos[i].getXmatriz() + veiculos[i].getNroBlocosOcupa(); j++)
		    					pista2[j+3] = true;
		    				break;
		    			case 3:
		    				for (int j = veiculos[i].getXmatriz(); j < veiculos[i].getXmatriz() + veiculos[i].getNroBlocosOcupa(); j++)
		    					pista3[j+3] = true;
		    				break;
		    		}
	    		}
	    		else {
	    			veiculoNoMapa[i]=false;
	    			veiculos[i].resetPosicao();
	    		}
	    	}
	    }
	    int soma1 = 0;
	    int soma2 = 0;
	    int soma3 = 0;
	    for (int i = 0; i < TAM_PISTA; i++) {
	    	if (pista1[i])
	    		soma1++;
	    	if (pista2[i])
	    		soma2++;
	    	if (pista3[i])
	    		soma3++;
	    }
	    if (soma1 < 0.3 * TAM_PISTA)
	    	tentaColocarVeiculo(pista1,1);
	    if (soma2 < 0.3 * TAM_PISTA)
	    	tentaColocarVeiculo(pista2,2);
	    if (soma3 < 0.3 * TAM_PISTA)
	    	tentaColocarVeiculo(pista3,3);
	    //Grava o tempo na saída do método
	    previous = System.currentTimeMillis();
		
	}
	
	private void tentaColocarVeiculo(Boolean pista[], int nroPista) {
		 int i = 0;
		 Boolean VeiculoFoiColocado = false;
		 while (i < NRO_VEICULOS && !VeiculoFoiColocado) {
			 if (!veiculoNoMapa[i]) {
				 Boolean CabeNaPista = true;
				 for (int j = 0; j <= veiculos[i].getNroBlocosOcupa(); j++) {
					 if (pista[j])
						 CabeNaPista = false;
				 }
				 if (CabeNaPista) {
					 for (int j = 0; j < veiculos[i].getNroBlocosOcupa(); j++)
						 pista[j] = true;
					 veiculoNoMapa[i] = true;
					 veiculos[i].setVisible(true);
					 veiculos[i].setPista(nroPista);
					 veiculos[i].setVisible(true);
					 VeiculoFoiColocado = true;
				 }
			
			 }
			 i++;
		 }
			
	}

	@Override
	public void renderGraphics() {
		// TODO Auto-generated method stub
		Graphics g = getBufferStrategy().getDrawGraphics();
		
		Graphics g2 = g.create(getInsets().left, 
	               getInsets().top, 
	               getWidth() - getInsets().right, 
	               getHeight() - getInsets().bottom);
	    //Limpamos a tela
	    g2.setColor(Color.GREEN);        
	    g2.fillRect(0, 0, getWidth(), getHeight());
	    if (mapa != null)
			mapa.draw((Graphics2D) g2);
		if (sapo != null)
			sapo.draw((Graphics2D) g2);
		for (int i = 0; i < NRO_VEICULOS; i++) {
			if(veiculoNoMapa[i]) {
				veiculos[i].draw((Graphics2D) g2);
			}
		}
		
		g.dispose(); //Liberamos o contexto criado.
		g2.dispose();
		
	}
	@Override
	public void paintScreen() {
	    if (!getBufferStrategy().contentsLost())
	        getBufferStrategy().show();
	}

	@Override
	public void tearDown() {
		//Não é realmente necessário, pois o jogo acaba.
		//Mas se fosse um fim de fase, seria.
		sapo = null;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				sapo.andaParaFrente();
				break;
			case KeyEvent.VK_S:
				sapo.andaParaTras();
				break;
			case KeyEvent.VK_A:
				sapo.andaParaEsquerda();
				break;
			case KeyEvent.VK_D:
				sapo.andaParaDireita();
				break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
