package Controle;

public class Moto extends Veiculo {
	private final String IMAGE_ADRESS = "/img/moto.png";
	private final int LARGURA = 54;
	private final int ALTURA = 50;
	private final int NRO_BLOCOS_OCUPA = 1;
	
	public Moto(int vel) {
		super(vel);
		super.setImage(IMAGE_ADRESS);
		super.setAltura(ALTURA);
		super.setLargura(LARGURA);
		super.setNroBlocosOcupa(NRO_BLOCOS_OCUPA);
	}

}
