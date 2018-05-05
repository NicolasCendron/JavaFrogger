package Controle;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Personagem extends SceneObject {

	private final int LIMITE_X_MATRIZ_MIN = 0;
	private final int LIMITE_Y_MATRIZ_MIN = 0;
	private final int LIMITE_X_MATRIZ_MAX = 6;
	private final int LIMITE_Y_MATRIZ_MAX = 4;
	private int Xmatriz;
	private int Ymatriz;

	
	protected Boolean colisaoParede() {
		if (Xmatriz < LIMITE_X_MATRIZ_MIN)
			return true;
		else if (Xmatriz > LIMITE_X_MATRIZ_MAX)
			return true;
		else if (Ymatriz > LIMITE_Y_MATRIZ_MAX)
			return true;
		else if (Ymatriz < LIMITE_Y_MATRIZ_MIN)
			return true;
		else
			return false;
	}

	public void setImage(String image_adress) {
		try {
			   super.setImage(ImageIO.read(Personagem.class.getResource(image_adress)));
			} catch (IOException e) {
				System.out.println("Erro ao carregar imagem " + image_adress);
			}
	}

	public int getXmatriz() {
		return Xmatriz;
	}

	public void setXmatriz(int xmatriz) {
		Xmatriz = xmatriz;
	}

	public int getYmatriz() {
		return Ymatriz;
	}

	public void setYmatriz(int ymatriz) {
		Ymatriz = ymatriz;
	}
}
