package Controle;

public class Sapo extends Personagem {

	private int vidas;
	private int score;
	private final int X_MATRIZ_INICIO = 3;
	private final int Y_MATRIZ_INICIO = 4;
	private final int VIDAS_INICIAL = 3;
	private final int X_INICIAL = 162;
	private final int Y_INICIAL = 270;
	private final int DESLOCAMENTO_X = 50;
	private final int DESLOCAMENTO_Y = 60;
	private final int LARGURA = 46;
	private final int ALTURA = 48;
	private final String IMAGEM_SAPO = "/img/sapinho.png";
	
	public Sapo() {
		super.setPosicaoX(X_INICIAL);
		super.setPosicaoY(Y_INICIAL);
		super.setImage(IMAGEM_SAPO);
		super.setAltura(ALTURA);
		super.setLargura(LARGURA);
		setVidas(VIDAS_INICIAL);
		super.setXmatriz(X_MATRIZ_INICIO);
		super.setYmatriz(Y_MATRIZ_INICIO);
	}
	public void andaParaFrente() {
		update(0,-1);
	}
	public void andaParaTras() {
		update(0,1);
	}
	public void andaParaEsquerda() {
		update(-1,0);
	}
	public void andaParaDireita() {
		update(1,0);
	}
	
	private void update(int desloc_x, int desloc_y) {
		super.setXmatriz(getXmatriz() + desloc_x);
		super.setYmatriz(getYmatriz() + desloc_y);
		if (colisaoParede()) {
			super.setXmatriz(getXmatriz() - desloc_x);
			super.setYmatriz(getYmatriz() - desloc_y);
		}
		else {
			super.setPosicaoX(getPosicaoX() + DESLOCAMENTO_X*desloc_x);
			super.setPosicaoY(getPosicaoY() + DESLOCAMENTO_Y*desloc_y);
		}
		
	}
	public void PerdeVida(){
		setVidas(getVidas() - 1);
	}
	public void IncrementaScore(int valor){
		setScore(getScore() + valor);
	}
	public void ResetaPosicao() {
		super.setPosicaoX(X_INICIAL);
		super.setPosicaoY(Y_INICIAL);
		super.setXmatriz(X_MATRIZ_INICIO);
		super.setYmatriz(Y_MATRIZ_INICIO);
	}
	public int getVidas() {
		return vidas;
	}
	public void setVidas(int vidas) {
		this.vidas = vidas;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
