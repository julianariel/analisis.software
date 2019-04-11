import java.io.*;
import java.time.Instant;

class bdatoa {
	public static void ps(String x) {
		System.out.print(x);

	}

	public static int LeerEntero() {
		String l�nea = new String("");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			l�nea = br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int ne = 0;
		try {
			ne = Integer.parseInt(l�nea);
		} catch (Exception e) {
		}
		;
		return (ne);
	}

	public static String LeerCadena() {
		String l�nea = new String("");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			l�nea = br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (l�nea);
	}

	public static void IngresoDatosPaciente() throws FileNotFoundException {
		String codpac, nompac, op = "";
		DataOutputStream datopac = null;
		datopac = new DataOutputStream(new FileOutputStream("C:\\datopac.txt"));
		try {

			do {
				ps("   ..............................................." + "\n");
				ps("   :-:  - D A T O S  D E L  P A C I E N T E -  :-:" + "\n");
				ps("   :-:.........................................:-:" + "\n");

				ps("Digite el codigo del paciente: ");
				codpac = LeerCadena();
				datopac.writeUTF(codpac);
				ps("Digite el nombre del paciente: ");
				nompac = LeerCadena();

				datopac.writeUTF(nompac);

				ps("Desea ingresar otro paciente? S/N" + "\n");

				op = LeerCadena();

			} while (op.equals("S") || op.equals("s"));
			datopac.close();
		} catch (IOException ioe) {
		}

	}

	public static void IngresarSituacionPaciente() throws FileNotFoundException {
		String codp = "", codm = "", enfpac, op = "";
		DataOutputStream situpac = null;
		situpac = new DataOutputStream(new FileOutputStream("C:\\situpac.txt"));

		try {
			do {

				ps("   ....................................................." + "\n");
				ps("   :-: - S I T U A C I O N  D E L  P A C I E N T E - :-:" + "\n");
				ps("   :-:...............................................:-:" + "\n");

				ps("Digite el codigo del paciente: ");
                codp = LeerCadena();
                situpac.writeUTF(codp);
				ps("Digite el codigo del medico: ");
				codm = LeerCadena();
				situpac.writeUTF(codm);
				ps("Digite el diagnostico del medico: ");
				enfpac = LeerCadena();
				situpac.writeUTF(enfpac);

				ps("Desea ingresar otro registro al historial? S/N");
				ps("\n");
				op = LeerCadena();

			} while (op.equals("S") || op.equals("s"));
			situpac.close();
		} catch (IOException ioe) {
		}
	}

	public static void IngresoDatosMedico() throws FileNotFoundException {
		String op = "", nommed, espmed, codmed;
		DataOutputStream datomed = null;
		datomed = new DataOutputStream(new FileOutputStream("C:\\datomed.txt"));
		try {
			do {

				ps("   ....................................................." + "\n");
				ps("   :-:      - D A T O S   D E L   M E D I C O -      :-:" + "\n");
				ps("   :-:...............................................:-:" + "\n");

				ps("Digite el codigo del medico: ");
				codmed = LeerCadena();
				datomed.writeUTF(codmed);

				ps("Digite el nombre del medico: ");
				nommed = LeerCadena();
				datomed.writeUTF(nommed);

				ps("Digite la especializacion del medico: ");
				espmed = LeerCadena();
				datomed.writeUTF(espmed);

				ps("Desea ingresar otro medico? S/N");
				ps("\n");

				op = LeerCadena();

			} while (op.equals("S") || op.equals("s"));
			datomed.close();
		} catch (IOException ioe) {
		}
	}

	public static void PacientesPorMedico() {
		
		double startTime = 0; 
		int sw = 0, sw1 = 0;
		String codtem, codm = "", nomm = "", codp = "", codme = "", codpa = "", nompa = "";
		try {
			ps("Digite el codigo del medico que desea consultar: ");
			codtem = LeerCadena();
			startTime = System.nanoTime();
			DataInputStream datomed = null;
			datomed = new DataInputStream(new FileInputStream("C:\\datomed.txt"));
			sw = 1;
			while (sw != 0) {
				try {
					codm = datomed.readUTF();
					nomm = datomed.readUTF();
					datomed.readUTF();
				} catch (EOFException e) {
					sw = 0;
				}
				datomed.close();
				if (codm.equals(codtem)) // compara el codigo extraido de la tabla "datomed" con el codigo digitado
				{
					ps("::: El medico " + nomm + " atiende a los siguientes pacientes: " + "\n");
					DataInputStream situpac = null;
					situpac = new DataInputStream(new FileInputStream("C:\\situpac.txt"));

					sw = 1;
					while (sw != 0) {
						try {
							codp = situpac.readUTF();
							codme = situpac.readUTF();
							situpac.readUTF();

							if (codme.equals(codtem)) // compara el codigo medico de la tabla "datomed" con el de la
														// tabla "situpac"
							{
								DataInputStream datopac = null;
								datopac = new DataInputStream(new FileInputStream("C:\\datopac.txt"));

								sw1 = 1;
								while (sw1 != 0) 
								{
									try {
										codpa = datopac.readUTF();
										nompa = datopac.readUTF();

										if (codpa.equals(codp)) // compara el codigo del
																// paciente de la tabla "situpac"
																// con el codigo del paciente de
																// la tabla "datopac"
										{
											ps("::: Paciente: " + nompa + "\n");
										}
									} catch (EOFException e) {
										sw1 = 0;
									}
									
								}
								
							}
						} catch (EOFException e) {
							sw = 0;
						}
					}

				}
			}

		} catch (IOException ioe) {
		}
		double endTime = System.nanoTime();
		System.out.println("Tiempo de Ejecucion: "+(endTime - startTime)/1000000+" ms");
	}

	public static void EnfermedadesPorMedico() {

		 
		try {
			String codtem, codm = "", nomm = "", codme = "", enfp = "";
			int sw = 0, sw1 = 0;
			ps("Digite el codigo del medico que desea consultar: ");
			codtem = LeerCadena();

			DataInputStream datomed = null;
			datomed = new DataInputStream(new FileInputStream("C:\\datomed.txt"));

			sw1 = 1;
			while (sw1 != 0) {
				try {
					codm = datomed.readUTF();
					nomm = datomed.readUTF();
					datomed.readUTF();

					if (codm.equals(codtem)) // compara el codigo digitado
												// con el codigo del medico de la
												// tabla "datomed"
					{
						ps("El medico " + nomm + " trata las siguientes enfermedades:" + "\n");

						DataInputStream situpac = null;
						situpac = new DataInputStream(new FileInputStream("C:\\situpac.txt"));

						sw = 1;
						while (sw != 0) {
							try {
								situpac.readUTF();
								codme = situpac.readUTF();
								enfp = situpac.readUTF();

								if (codtem.equals(codme)) // compara el codigo del medico
															// de la tabla "datomed"
															// con el codigo del medico en la
															// tabla "situpac"

								{
									ps(">>>> " + enfp + "\n");
								}
							} catch (EOFException e) {
								sw = 0;
							}
						}
						situpac.close();
					}
				} catch (EOFException e) {
					sw1 = 0;
				}
			}
			datomed.close();

		} catch (IOException ioe) {
		}

	
	}

	public static void VerificarOpcion1(int op1) {
		if (op1 < 1 || op1 > 3)
			ps("Debe digitar una opcion del menu" + "\n");
	}

	public static void VerificarOpcion2(int op2) {
		if (op2 < 1 || op2 > 4)
			ps("Debe digitar una opcion del menu" + "\n");
	}
	
	public static void metodoAuxiliar(int op2)
	{
		switch (op2) {
		case 1: // lista pacientes los pacientes que atiende un medico especifico
			PacientesPorMedico();
			break;

		case 2: // lista las enfermedades que atiende cada medico
			EnfermedadesPorMedico();
			break;
		}
	}

	public static void main(String args[]) throws Exception {
		int op1, op2; // variables de selecci�n usadas en los diferentes men�s

		do {
			op1 = 0;

			ps("   .............................................." + "\n");
			ps("   :-:        C E N T R O  M E D I C O        :-:" + "\n");
			ps("   :-:   >>>> L O S  L A U R E L E S <<<<     :-:" + "\n");
			ps("   :-:  C O N T R O L  D E  P A C I E N T E S :-:" + "\n");
			ps("   :-:........................................:-:" + "\n");
			ps("   :-: 1.  Ingreso de datos                   :-:" + "\n");
			ps("   :-: 2.  Informes                           :-:" + "\n");
			ps("   :-: 3.  Salir                              :-:" + "\n");
			ps("   .............................................." + "\n");
			ps("   ....Elija la opcion deseada: ");

op1 = LeerEntero();

			VerificarOpcion1(op1);

			if (op1 == 1) // seleci�n ingreso de pacientes
			{

				do {

					ps("   ..............................................." + "\n");
					ps("   :-: -I N G R E S O  D E  P A C I E N T E S- :-:" + "\n");
					ps("   :-:.........................................:-:" + "\n");
					ps("   :-: 1.  Datos del paciente                  :-:" + "\n");
					ps("   :-: 2.  Situacion del paciente              :-:" + "\n");
					ps("   :-: 3.  Datos del medico                    :-:" + "\n");
					ps("   :-: 4.  Anterior                            :-:" + "\n");
					ps("   ..............................................." + "\n");
					ps("   ....Elija la opcion deseada: ");

					op2 = LeerEntero();

					VerificarOpcion1(op2);

					switch (op2)
					{
					case 1: // ingreso de datos, datos del paciente
						IngresoDatosPaciente();
						break;

					case 2: // ingreso de datos, situacion del paciente
						IngresarSituacionPaciente();
						break;

					case 3: // ingreso de datos, datos del medico
						IngresoDatosMedico();
						break;
					}
				} while (op2 != 4);
			}
			else if (op1 == 2) // seleci�n informes
			{

				do {
					ps("   ..............................................." + "\n");
					ps("   :-:  C O N T R O L  D E  P A C I E N T E S  :-:" + "\n");
					ps("   ..............................................." + "\n");
					ps("   :-:           - I N F O R M E S -           :-:" + "\n");
					ps("   :-:.........................................:-:" + "\n");
					ps("   :-: 1. Listado de pacientes por medico      :-:" + "\n");
					ps("   :-: 2. Enfermedades que atiende cada medico :-:" + "\n");
					ps("   :-: 3. Anterior                             :-:" + "\n");
					ps("   ..............................................." + "\n");
					ps("   ....Elija la opcion deseada: ");

					op2 = LeerEntero();

					VerificarOpcion1(op2);

					metodoAuxiliar(op2);

				} while (op2 != 3);

			}

		} while (op1 != 3);

	}
}
