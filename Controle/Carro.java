package Controle;

public class Carro extends Veiculo {
	private final String IMAGE_ADRESS = "/img/carro.png";
	private final int LARGURA = 108;
	private final int ALTURA = 50;
	private final int NRO_BLOCOS_OCUPA = 2;
	
	public Carro(int vel) {
		super(vel);
		super.setImage(IMAGE_ADRESS);
		super.setAltura(ALTURA);
		super.setLargura(LARGURA);
		super.setNroBlocosOcupa(NRO_BLOCOS_OCUPA);
	}

}
