package com.ioc.rallyioc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import com.ioc.motor.FileIO;

//Aquesta classe guarda la configuració que s'ha de mantenir entre diferents partides
public class Configuracio {
	// Taula de rècords dels jugadors de major a menor (valors inicials)
	public static int[] records = new int[] { 1000, 500, 250, 100, 50 };

	// Determina si el so del joc està activat o no
	public static boolean soActivat = true;

	// Afegeix una puntuació als rècords. Retorna si la puntuació
	// ha estat afegida a l'array de rècords o no
	public static boolean afegirMarcador(int puntuacio) {
		// Mirem si cal actualitzar la puntuació màxima
		for (int i = 0; i < 5; i++) {
			if (records[i] < puntuacio) {
				for (int j = 4; j > i; j--)
					records[j] = records[j - 1];
				records[i] = puntuacio;
				return true;
			}
		}
		return false;
	}

	// Carreguem la configuració de memòria
	public static void carregaConfiguracio(FileIO fitxer) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					fitxer.llegirFitxer("rallyIOC.conf")));

			// Llegim les opcions
			soActivat = Boolean.parseBoolean(in.readLine());

			for (int i = 0; i < 5; i++) {
				records[i] = Integer.parseInt(in.readLine());
			}
		} catch (Exception e) {
			// Si hi ha algun problema fem servir els valors estàndard
		} finally {
			// Tanquem l'InputStream
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
			}
		}
	}

	// Guardem la configuració en memòria
	public static void desaConfiguracio(FileIO fitxer) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					fitxer.escriureFitxer("rallyIOC.conf")));

			// Escrivim les opcions
			out.write(Boolean.toString(soActivat));
			out.write("\n");

			for (int i = 0; i < 5; i++) {
				out.write(Integer.toString(records[i]));
				out.write("\n");
			}
		} catch (Exception e) {
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
			}
		}
	}
}