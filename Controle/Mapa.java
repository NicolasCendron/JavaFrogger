package Controle;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mapa extends SceneObject {
	private final String IMAGE_ADRESS = "/img/mapa.png";
	private final int X_MAPA = -16;
	private final int Y_MAPA = 26;
	private final int ALTURA_MAPA = 302;
	private final int LARGURA_MAPA = 388;
	
	public Mapa() {
		setImage(IMAGE_ADRESS);
		super.setPosicaoX(X_MAPA);
		super.setPosicaoY(Y_MAPA);
		super.setAltura(ALTURA_MAPA);
		super.setLargura(LARGURA_MAPA);
	}
	private void setImage(String image_adress) {
		try {
			   super.setImage(ImageIO.read(Personagem.class.getResource(image_adress)));
			} catch (IOException e) {
				System.out.println("Erro ao carregar imagem " + image_adress);
			}
	}
}
