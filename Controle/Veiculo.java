package Controle;

public class Veiculo extends Personagem {
	
	private int velocidade;
	private Boolean visible;
	private int NroBlocosOcupa;
	private final float TAM_BLOCO = 54;
	protected final int POS_PISTA1_Y = 210;
	protected final int POS_PISTA2_Y = 150;
	protected final int POS_PISTA3_Y = 90;
	protected final int POS_PISTA1_X = -162;
	protected final int POS_PISTA2_X = -162;
	protected final int POS_PISTA3_X = -162;
	private int pista;
	
	public void resetPosicao() {
		setPista(pista);
	}
	public Veiculo(int vel) {
		velocidade = vel;
	}
	public void trocaPista(int direcao) {
		if (pista + direcao >= 3 && pista + direcao <= 1)
			pista += direcao;
	}
	public void AumentaVelocidade(int aumento) {
		velocidade += aumento;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	
	public void update(long time) {
	    //O tempo é em milis. Para obter em segundos, precisamos dividi-lo por 1000.        
	    setPosicaoX((int) ((time * velocidade) / 1000 + getPosicaoX()));
	    calculaPosicaoMatriz();
	    if(getXmatriz() >= 0) {
	    	if (colisaoParede()) {
				visible = false;
	    	}
	    }
	}
	private void calculaPosicaoMatriz() {
		setXmatriz(Math.round(getPosicaoX()/TAM_BLOCO));
	}
	public int getVelocidade() {
		return velocidade;
	}
	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}

	public int getNroBlocosOcupa() {
		return NroBlocosOcupa;
	}

	public void setNroBlocosOcupa(int nroBlocosOcupa) {
		NroBlocosOcupa = nroBlocosOcupa;
	}

	public int getPista() {
		return pista;
	}

	public void setPista(int pista) {
		this.pista = pista;
		switch (pista) {
			case 1:
				super.setPosicaoX(POS_PISTA1_X);
				super.setPosicaoY(POS_PISTA1_Y);
				super.setYmatriz(3);
				break;
			case 2:
				super.setPosicaoX(POS_PISTA2_X);
				super.setPosicaoY(POS_PISTA2_Y);
				super.setYmatriz(2);
				break;
			case 3:
				super.setPosicaoX(POS_PISTA3_X);
				super.setPosicaoY(POS_PISTA3_Y);
				super.setYmatriz(1);
				break;
		}
	}
	
}
