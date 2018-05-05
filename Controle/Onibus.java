package Controle;

public class Onibus extends Veiculo {
	
	private final String IMAGE_ADRESS = "/img/onibus.png";
	private final int LARGURA = 162;
	private final int ALTURA = 50;
	private final int NRO_BLOCOS_OCUPA = 3;
	
	public Onibus(int vel) {
		super(vel);
		super.setImage(IMAGE_ADRESS);
		super.setAltura(ALTURA);
		super.setLargura(LARGURA);
		super.setNroBlocosOcupa(NRO_BLOCOS_OCUPA);
	}
}
