package Controle;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class SceneObject {
	
	private int posicaoX;
	private int posicaoY;
	private BufferedImage image;
	private int altura;
	private int largura;
	
	public void draw(Graphics2D g) {
		g.drawImage(getImage(), getPosicaoX(), getPosicaoY(), getLargura(), getAltura(), null);
	}
	
	public int getPosicaoX() {
		return posicaoX;
	}
	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}
	public int getPosicaoY() {
		return posicaoY;
	}
	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}
	public int getLargura() {
		return largura;
	}
	public void setLargura(int largura) {
		this.largura = largura;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
