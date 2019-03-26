package pos_tagger_java;

public class ProgramaPrincipal {

	public ProgramaPrincipal() {
		
	}

	public static void main(String[] args) {

		PosTagger meuPosTagger = new PosTagger();
		
		meuPosTagger.analisarArquivo("      "); //definir aqui o caminho do arquivo com o corpus textual

	}

}
