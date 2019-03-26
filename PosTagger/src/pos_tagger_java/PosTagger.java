package pos_tagger_java;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class PosTagger {

	public PosTagger() {

	}

	public void analisarArquivo(String arquivo) {

		InputStream tokenModelIn = null;
		InputStream posModelIn = null;

		String texto = "";
		try
		{
			System.out.println("\nnomeArquivo: " + arquivo);
			texto = new String(Files.readAllBytes(Paths.get(arquivo)), "UTF-8");
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return;
		}

//		System.out.println("\n##### Início do arquivo: #####");
//		System.out.println(texto);
//		System.out.println("\n##### Fim do arquivo: #####");

		try {

			System.out.println("\nInicializando etiquetagem. Aguarde...");

			// Os modelos em português devem estar no diretório <opennlp-models>, na raiz do projeto
			
			tokenModelIn = new FileInputStream("opennlp-models/pt-token.bin");
			TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
			Tokenizer tokenizer = new TokenizerME(tokenModel);
			String tokens[] = tokenizer.tokenize(texto);

			posModelIn = new FileInputStream("opennlp-models/pt-pos-maxent.bin");
			POSModel posModel = new POSModel(posModelIn);
			POSTaggerME posTagger = new POSTaggerME(posModel);
			
			String tags[] = posTagger.tag(tokens);
			
			//se desejar, altere o nome do arquivo de saída 
		    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("texto etiquetado (pos tags).txt"), "UTF-8"));

			writer.write("Token\t:\tTag\n---------------------------------------------\n\n");

			for(int i=0;i<tokens.length;i++){
				
				writer.write(tokens[i] + "\t:\t" + tags[i] + "\n");
			}
			
			writer.close();	

			System.out.println("Etiquetagem finalizada.");

		}
		catch (IOException e) {

			e.printStackTrace();
		}
		
		finally {
			
			if (tokenModelIn != null) {
				
				try {
					tokenModelIn.close();
				}
				catch (IOException e) {
				}
				
			}
			
			if (posModelIn != null) {
				
				try {
					posModelIn.close();
				}
				catch (IOException e) {
				}
				
			}
		}
	}
}
