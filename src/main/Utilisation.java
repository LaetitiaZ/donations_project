package main;


import java.util.Scanner;

import module_personne.Adherent;
import module_personne.Beneficiaire;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.util.List;




 
public class Utilisation {
	
	

	
	public static List<List<String>> Read(File file) throws IOException
	{
		
		Path path = Paths.get(file.getAbsolutePath()); 
		List<List<String>> personnes = new ArrayList<>();
		List<String> liste = new ArrayList<>();
		List<String> lignes = Files.readAllLines(path, Charset.defaultCharset()); 
		for (String ligne : lignes)
		{
			for (String caracter : ligne.split(";"))
			{
				liste.add(caracter);
			}
			List<String> listebis = new ArrayList<>(liste);
			personnes.add(listebis);
			liste.clear();
		}
		return personnes;
	}
	
	public static void Ajout_Personne(File file, List<String> Personne) throws IOException
	{
		 
		BufferedWriter ajout = new BufferedWriter(new FileWriter(file, true));
		ajout.newLine();
		for (int i=0;i<Personne.size();i++)
		{
			if(i==0)
			{
				ajout.write(Personne.get(i));
			}
			else
			{
				ajout.write(";"+Personne.get(i));
			}
			
		}
		ajout.close();
	}
		
	
	
	public static void RechercheTel(File file, String telephone) throws IOException
	{
		List<List<String>> liste_recherche = new ArrayList<>(Read(file));
		boolean bol = false;
		for (int p = 0 ; p < liste_recherche.size() ; p++)
		{
			if (liste_recherche.get(p).get(3).equals(telephone))
				{
					System.out.println(liste_recherche.get(p));
					bol = true;
				}
		}
		if (bol == false)
		{
			System.out.println("Il n'y a pas de membre qui possède ce numéro.");
		}
	}
	
	public static void Supprimer_Personne(File file, int numero) throws IOException
	{
		File tmp = new File("fichier_modifie.txt");
		BufferedWriter remplir = new BufferedWriter(new FileWriter(tmp,false));
		List<List<String>> liste = new ArrayList<>(Read(file));
		for (int i=0; i<liste.size();i++)
		{
			if(liste.get(i).get(3)!=String.valueOf(numero))
			{
				for(String chaine : liste.get(i))
				{
					if(chaine!=liste.get(i).get(liste.size()-1))
					{
						remplir.write(chaine+";");
					}
					else
					{
						remplir.write(chaine);
					}
				}
				remplir.newLine();
			}
			else //met à jour les références
			{
				for(int k=i+1;k<liste.size();k++)
				{
					int new_ref=Integer.valueOf(liste.get(k).get(0))-1;
					String new_reference = String.valueOf(new_ref);
					liste.get(k).set(0, new_reference);

				}
			}
			
		}
		remplir.close();
		file.delete();
		tmp.renameTo(file);
		
	}
	
	public static void Modifier(File file, int tel, String element_supp, String element_modifie) throws IOException
	{
	
		File tmp = new File("fichier_modifie.txt");
		BufferedWriter modif = new BufferedWriter(new FileWriter(tmp,false));
		List<List<String>> liste = new ArrayList<>(Read(file));
		for (int i=0; i<liste.size();i++)
		{
			for (int k =0; k<liste.size();k++)
			{
				if(liste.get(i).get(k).contentEquals(element_supp) && Integer.valueOf(liste.get(i).get(3))==tel)
				{
					liste.get(i).set(k,element_modifie);
				}
			}
		
			for(String chaine : liste.get(i))
				{
					if(chaine!=liste.get(i).get(liste.size()-1))
					{
						modif.write(chaine+";");
					}
					else
					{
						modif.write(chaine);
					}
				}
				modif.newLine();
			}
			
		modif.close();
		file.delete();
		tmp.renameTo(file);
		
	}
	
	public static void RechercheNom(File file, String Nom) throws IOException
	{
		List<List<String>> liste_recherche = new ArrayList<>(Read(file));
		boolean bol = false;
		for (int p = 0 ; p < liste_recherche.size() ; p++)
		{
			if (liste_recherche.get(p).get(1).equals(Nom))
				{
					System.out.println(liste_recherche.get(p));
					bol = true;
				}
		}
		if (bol == false)
		{
			System.out.println("Il n'y a pas de membre qui possède ce numéro.");
		}
	}
	
	public static void Ajout_Don(File file, List<String> don) throws IOException
	{
		BufferedWriter ajout = new BufferedWriter(new FileWriter(file));
		
		ajout.write(don.get(2)+";"+don.get(1)+";"+don.get(0)+";"+don.get(3)+";"+don.get(4)+";"+don.get(5)+";"+don.get(6));
		ajout.newLine();
		ajout.close();
	}

	public static void Supprimer_Don(File file, String reference) throws IOException
	{
			File tmp = new File("fichier_modifie_don.txt");
			BufferedWriter remplir = new BufferedWriter(new FileWriter(tmp,false));
			List<List<String>> liste = new ArrayList<>(Read(file));
			for (int i=0; i<liste.size();i++)
			{
				if(liste.get(i).get(2)==reference)
				{
					for(String chaine : liste.get(i))
					{
						if(chaine!=liste.get(i).get(liste.size()-1))
						{
							remplir.write(chaine+";");
						}
						else
						{
							remplir.write(chaine);
						}
					}
					remplir.newLine();
				}
				else //met à jour les références
				{
					for(int k=i+1;k<liste.size();k++)
					{
						int new_ref=Integer.valueOf(liste.get(k).get(0))-1;
						String new_reference = String.valueOf(new_ref);
						liste.get(k).set(0, new_reference);

					}
				}
				
			}
			remplir.close();
			file.delete();
			tmp.renameTo(file);
			
	}
	
	public static void Ajout_Vente(File file, String nom, int montant) throws IOException
	{
		BufferedWriter ajout = new BufferedWriter(new FileWriter(file));
		ajout.write(nom+";"+montant);
		ajout.newLine();
		ajout.close();
		
	}
	
	public static int Nombre_Dons(File file) throws IOException
	{
		List<List<String>> liste_dons = new ArrayList<>(Read(file));
		return liste_dons.size();
	}
	

	public static void main(String[] args) throws IOException
	{
		int date;
		
		
		String nom="";
		String description="";
		String Type_materiel="";
		float montant;
		
		
	
		List<String> Mobilier = new ArrayList<>();
		List<String> Mobilier_Chambre = new ArrayList<>();
		List<String> Mobilier_SC = new ArrayList<>();
		List<String> Type_Table = new ArrayList<>();
		List<String> Forme_Table = new ArrayList<>();
		List<String> Personne = new ArrayList<>();
		
		Personne.add("entrepot");
		Personne.add("depot_vente");


		
		Mobilier.add("Mobilier Chambre");
		Mobilier.add("Mobilier Salle/Cuisine");
		Mobilier_Chambre.add("Matelas");
		Mobilier_Chambre.add("Chevets");
		Mobilier_Chambre.add("Armoires");
		Mobilier_Chambre.add("Table");
		Mobilier_SC.add("Chaises");
		Mobilier_SC.add("Electro-ménager");
		Mobilier_SC.add("Cuisinière");
		Mobilier_SC.add("Réfrigérateur");
		Mobilier_SC.add("Lave-linge");
		Mobilier_SC.add("Vaisselle");
		Mobilier_SC.add("Couverts");
		Mobilier_SC.add("Assiettes");
		Type_Table.add("Cuisine");
		Type_Table.add("Salon");
		Forme_Table.add("Rectangulaire");
		Forme_Table.add("Carrée");
		Forme_Table.add("Ronde");
		
	
		
		File archive_ventes = new File("archive_ventes.txt");
		
		
		File fichier_p = new File("fichier_personnes_morale.txt");
		if (fichier_p.createNewFile())
		{
			BufferedWriter fichier_personnes = new BufferedWriter(new FileWriter(fichier_p, false));
			fichier_personnes.write("1;Entrepot_A;rue des Fleurs Paris;0123456789;entrepot");
			fichier_personnes.newLine();
			fichier_personnes.write("2;Depot_Vente_A;rue des Paquerettes Paris;0102030506;depot_vente");
			fichier_personnes.newLine();
			fichier_personnes.write("3;MobilierPourTous;rue des Coeurs Paris;0105040302;association");
			
			fichier_personnes.close(); 
		}
		
		File fichier_a = new File("fichier_adherents.txt");
		if (fichier_a.createNewFile())
		{
			BufferedWriter fichier_adherents = new BufferedWriter(new FileWriter(fichier_a,false));	
		
		fichier_adherents.write("1;Durand;rue des Sablons Paris;0102030405;Arthur;president");
		fichier_adherents.newLine();
		fichier_adherents.write("2;Dupond;rue de la République Paris;0103047676;Simon;tresorier");
		fichier_adherents.newLine();
		fichier_adherents.write("3;Courty;rue Huchette Paris;0135362578;Valentin;membre");
		fichier_adherents.newLine();
		fichier_adherents.write("4;Legrand;rue Pompidou Paris;0178766567;Claude;membre");
		fichier_adherents.newLine();
		fichier_adherents.write("5;Lepetit;rue Mitterand Paris;0698788237;Claudine;membre");
		fichier_adherents.newLine();
		fichier_adherents.write("6;Legros;rue FelixFaure Paris;0176789876;Claire;membre");
		
		fichier_adherents.close();
		}
		
		
		File fichier_b = new File("fichier_beneficiaires.txt");
		if (fichier_b.createNewFile())
		{
		BufferedWriter fichier_beneficiaires = new BufferedWriter(new FileWriter(fichier_b,false));
		
		fichier_beneficiaires.write("1;Leforgeron;rue de la Convention Paris;0198909887;Armand;10/10/1930");
		fichier_beneficiaires.newLine();
		fichier_beneficiaires.write("2;Lemarechal;rue Molitor Paris;0121298731;Amandine;11/02/1928");
		fichier_beneficiaires.newLine();
		fichier_beneficiaires.write("3;Levaillant;rue de la Gare Paris;0123988776;Louise;23/03/1934");
		
		fichier_beneficiaires.close();
		
		}
		
		File archive_dons_acceptes  = new File("archive_dons_acceptes.txt");
		File archive_dons_refuses = new File("archive_dons_refuses.txt");
		File archive_dons_proposes = new File("archive_dons_proposes.txt");
		File archive_dons_en_traitement = new File("archive_dons_traitement.txt");
		File archive_dons_vendus = new File("archive_dons_vendus.txt");
		File archive_dons_stockes = new File("archive_dons_stockes.txt");
		File archive_dons_demandes = new File("archive_dons_demandes.txt");
		File archive_dons_depot_vente = new File("archive_dons_depot_vente.txt");
		File archive_dons_entrepot = new File("archive_dons_entrepot.txt");
		File archive_dons_volumineux_recus = new File("archive_dons_volumineux.txt");
		File archive_dons_volumineux_acceptes = new File("archive_dons_volumineux_a.txt");
		

		
		System.out.println("Bonjour, qui êtes-vous?");
		System.out.println("1. Adhérent");
		System.out.println("2. Bénéficiaire");
		System.out.println("3. Nouvel Adhérent");
		System.out.println("4. Nouveau Bénéficiaire");
		System.out.println("Veuillez entrer le numéro correspondant : ");
		
		List<List<String>> liste_adherents = Read(fichier_a);
		List<List<String>> liste_beneficiaires = Read(fichier_b);
		List<List<String>> liste_consideree = new ArrayList<>();
		List<String> utilisateur = new ArrayList<>();
	
		
		boolean ide = true;
		boolean numm = true;
		boolean personne = true;
		
		Scanner kb = new Scanner(System.in);
		while (personne)
		{
			
			try
			{
				personne=false;
				
				int number = Integer.parseInt(kb.nextLine());
				
				if (number==1)
				{
					liste_consideree=new ArrayList<>(liste_adherents);
				}
				else if (number==2)
				{
					liste_consideree=new ArrayList<>(liste_beneficiaires);
				}
				else if (number==3)
				{
					System.out.println("Votre nom (sans oublier la majuscule)?");
					String name = kb.next();
					System.out.println("Combien de caractères (espacés par des espaces et sans le numéro) possède votre adresse?");
					
					
					
					System.out.println("Entrez le nombre: ");
					int limite = Integer.parseInt(kb.next());
					System.out.println("Votre adresse(sans le numéro)? (Veuillez entrer chacun des caractères à la ligne)");
					String adresse= kb.next();
					for(int f=0;f<limite-1;f++)
					{
						adresse+=" "+kb.next();
					}
					
					System.out.println(adresse);
					
					System.out.println("Votre numéro de téléphone?");
					String tm = kb.next();
					System.out.println("Votre prénom?");
					String first_name = kb.next();
					
					int reference=liste_adherents.size()+1;
					Adherent considered = new Adherent(reference,name,adresse,Integer.valueOf(tm),first_name,"membre");
					List<String> adherent = new ArrayList<>(considered.ConvertToList());
					Ajout_Personne(fichier_a,adherent);
					System.out.println();
					System.out.println("Veuillez re-éxecuter le code pour avoir accès aux services en utilisant vos nouveaux identifiants");
					
				}
				else if (number==4)
				{
					System.out.println("Votre nom (sans oublier la majuscule)?");
					String name = kb.next();
					System.out.println("Combien de caractères (espacés par des espaces et sans le numéro) possède votre adresse?");
					System.out.println("Entrez le nombre: ");
					int limite = Integer.parseInt(kb.next());
					System.out.println("Votre adresse(sans le numéro)? (Veuillez entrer chacun des caractères à la ligne)");
					String adresse= kb.next();
					for(int f=0;f<limite-1;f++)
					{
						adresse+=" "+kb.next();
					}
					
					System.out.println(adresse);
					
					System.out.println("Votre numéro de téléphone?");
					String tm = kb.next();
					System.out.println("Votre prénom?");
					String first_name = kb.next();
					System.out.println("Votre date de naissance? (sous la forme JJ/MM/YEAR)");
					String birthdate = kb.next();
					
					int reference=liste_adherents.size()+1;
					Beneficiaire considered = new Beneficiaire(reference,name,adresse,Integer.valueOf(tm),first_name,birthdate);
					List<String> beneficiaire = new ArrayList<>(considered.ConvertToList());
					Ajout_Personne(fichier_b,beneficiaire);
					System.out.println();
					System.out.println("Veuillez re-éxecuter le code pour avoir accès aux services en utilisant vos nouveaux identifiants");
					
				}
				
				else
				{
					System.out.println("L'entrée n'est pas acceptée, veuillez recommencer : ");
					personne=true;
				}
				if (number==1 || number==2)
				{
					
					
					while(ide)
					{
						try
						{	System.out.println();
							System.out.println("Veuillez saisir votre numéro de téléphone: ");
							int mobile = kb.nextInt();
							for (List<String> membre : liste_consideree)
							{
							if(mobile==Integer.parseInt(membre.get(3)))
								{
								ide=false;
								membre=utilisateur;
								}
								
							
								
							}
						}
						catch (NumberFormatException e)
						{
							System.out.println("Le numéro de téléphone est incompatible");
							ide=true;
						}
									
					}
				}
				
			}
			catch (NumberFormatException e)
			{
				System.out.print("L'entrée n'est pas un nombre, entrer un nombre : ");
				personne=true;
			}
		
		}
		boolean keep_going = true;
		while(keep_going)
		{
		boolean nombre = true;
		while (nombre)
		{
			try {
			System.out.println();
			System.out.println("1. Module Personne");
			System.out.println("2. Module Don");
			System.out.println("3. Module Recherche");
			System.out.println("4. Module Statistique");
			System.out.println();
			System.out.print("Entrez le numéro du module choisi : ");
		
	
			int num = kb.nextInt();
			nombre = false;
			if (num==1)
			{
				System.out.println();
				System.out.println("Quel dossier vous intéresse?");
				System.out.println("1. Adhérents");
				System.out.println("2. Bénéficiaires");
				System.out.println("3. Morales");
				
				
				int dossier = Integer.parseInt(kb.next());
				
				
				if (dossier == 1)
				{
					System.out.println();
					System.out.println("Que vous voulez faire?");
					System.out.println("1. Lire le dossier ");
					System.out.println("2. Faire une recherche ");
					System.out.println("3. Modifier les données d'un adhérent");
					System.out.println("4. Supprimer un adhérent");
					System.out.print("Veuillez entrer le numéro correspondant: "); 
					int a = Integer.parseInt(kb.next());
					if ( a == 1)
					{
						System.out.println(Read(fichier_a));
					}
					else if ( a== 2)
					{	System.out.println();
						System.out.println("Voulez-vous faire une recherche par: ");
						System.out.println("1. Téléphone");
						System.out.println("2. Nom");
						System.out.println("Veuillez entrer le numéro correspondant");
						int z = Integer.parseInt(kb.next());
						
						if (z == 1)
						{
							
							System.out.print("Veuillez rentrer le numéro de téléphone");
							String t = kb.next();
							RechercheTel(fichier_a, t);
						}
						else if ( z == 2)
						{
				
							System.out.println("Veuillez rentrer le nom");
							String n = kb.next();
							RechercheNom(fichier_a, n);
						}
					}
					else if ( a== 3)
					{
						boolean encore = true;
						while(encore)
							
						{
						System.out.println();
						System.out.println("Qui est l'adhérent concerné?");
						System.out.println("Veuillez saisir le numéro de téléphone");
						int numero_tel = Integer.parseInt(kb.next());
						System.out.println("Veuillez saisir l'élément actuel à modifier (Par ex : \"Durand\" ou \"010203040506\" ) : ");
						String current = kb.next();
						System.out.println("Veuillez saisir le nouvel élément: ");
						String modified = kb.next();
						Modifier(fichier_a, numero_tel, current, modified);
						System.out.println("Voulez-vous modifiier autre chose?");
						System.out.println("1. Oui");
						System.out.println("2. Non");
						System.out.println("Veuillez saisir le numéro correspondant à votre choix");
						int choix = Integer.parseInt(kb.next());
						if (choix==1)
						{
							encore=true;
						}
						else if(choix ==2)
						{
							encore = false;
						}
						else
						{
							boolean erreur = true;
							while (erreur)
							{
								System.out.println("L'entrée n'est pas acceptée, veuillez recommencer: ");
								int choix2 = Integer.parseInt(kb.next());
								if (choix2==1 || choix2==2)
									{
										erreur = false;
										if(choix2==2)
										{
											encore=false;
										}
									}
								
							}
							
							
						}
						}
					}
					else if ( a == 4)
					{
						List<List<String>> liste_membre = new ArrayList<>(Read(fichier_a));
						System.out.println("Quel adhérent voulez-vous supprimer (Veuillez saisir son numéro de téléphone)");
						int num_tel = Integer.parseInt(kb.next());
						boolean nume =true;
						while(nume)
						{
							for (int c=0; c<liste_membre.size();c++)
							{
								if(Integer.parseInt(liste_membre.get(c).get(3))==num_tel)
								{
								Supprimer_Personne(fichier_a, num_tel);
								nume=false;
								}
								
							}
							if (nume==true)
							{
								System.out.println("Le numéro entré ne correspond à aucun adhérent, veuillez recommencer: ");
							}
						}
					}
					
				}
						
				else if (dossier == 2)
				{
					System.out.println("Que vous voulez faire?");
					System.out.println("1. Lire le dossier ");
					System.out.println("2. Faire une recherche ");
					System.out.println("3. Modifier les données d'un bénéficiaire");
					System.out.println("4. Supprimer un bénéficiaire");
					System.out.print("Veuillez entrer le numéro correspondant: "); 
					int b = Integer.parseInt(kb.next());
					if ( b == 1)
					{
						System.out.println(Read(fichier_b));
					}
					else if ( b== 2)
					{	
						System.out.println("Voulez-vous faire une recherche par: ");
						System.out.println("1. Téléphone");
						System.out.println("2. Nom");
						System.out.println("Veuillez entrer le numéro correspondant");
						int z = Integer.parseInt(kb.next());
						
						if (z == 1)
						{
				
							System.out.println("Veuillez rentrer le numéro de téléphone");
							String t = kb.next();
							RechercheTel(fichier_b, t);
						}
						else if ( z == 2)
						{
				
							System.out.println("Veuillez rentrer le nom");
							String n = kb.next();
							RechercheNom(fichier_b, n);
						}
					}
					else if ( b== 3)
					{
						boolean encore = true;
						while(encore)
							
						{
						System.out.println("Qui est l'adhérent concerné?");
						System.out.println("Veuillez saisir le numéro de téléphone");
						int numero_tel = Integer.parseInt(kb.next());
						System.out.println("Veuillez saisir l'élément actuel à modifier (Par ex : \"Durand\" ou \"010203040506\" : ");
						String current = kb.next();
						System.out.println("Veuillez saisir le nouvel élément: ");
						String modified = kb.next();
						Modifier(fichier_b, numero_tel, current, modified);
						System.out.println("Voulez-vous modifiier autre chose?");
						System.out.println("1. Oui");
						System.out.println("2. Non");
						System.out.println("Veuillez saisir le numéro correspondant à votre choix");
						int choix = Integer.parseInt(kb.next());
						if (choix==1)
						{
							encore=true;
						}
						else if(choix ==2)
						{
							encore = false;
						}
						else
						{
							boolean erreur = true;
							while (erreur)
							{
								System.out.println("L'entrée n'est pas acceptée, veuillez recommencer: ");
								int choix2 = Integer.parseInt(kb.next());
								if (choix2==1 || choix2==2)
									{
										erreur = false;
										if(choix2==2)
										{
											encore=false;
										}
									}
								
							}
							
							
						}
						}
					}
					else if (b == 4)
					{
						List<List<String>> liste_membre = new ArrayList<>(Read(fichier_b));
						System.out.println("Quel bénéficiaire voulez-vous supprimer (Veuillez saisir son numéro de téléphone)");
						int num_tel = Integer.parseInt(kb.next());
						boolean nume =true;
						while(nume)
						{
							for (int c=0; c<liste_membre.size();c++)
							{
								if(Integer.parseInt(liste_membre.get(c).get(3))==num_tel)
								{
								Supprimer_Personne(fichier_b, num_tel);
								nume=false;
								}
								
							}
							if (nume==true)
							{
								System.out.println("Le numéro entré ne correspond à aucun bénéficiaire, veuillez recommencer: ");
							}
						}
					}
				}
						
				else if (dossier == 3)
				{
	
						System.out.println("Que vous voulez faire?");
						System.out.println("1. Lire le dossier ");
						System.out.println("2. Faire une recherche ");
						System.out.println("3. Modifier les données d'une personne morale");
						System.out.println("4. Supprimer une personne morale");
						System.out.print("Veuillez entrer le numéro correspondant: "); 
						int a = Integer.parseInt(kb.next());
						if ( a == 1)
						{
							System.out.println(Read(fichier_p));
						}
						else if ( a== 2)
						{	
							System.out.println("Voulez-vous faire une recherche par: ");
							System.out.println("1. Téléphone");
							System.out.println("2. Nom");
							System.out.println("Veuillez entrer le numéro correspondant");
							int z = Integer.parseInt(kb.next());
							
							if (z == 1)
							{
					
								System.out.print("Veuillez rentrer le numéro de téléphone");
								String t = kb.next();
								RechercheTel(fichier_p, t);
							}
							else if ( z == 2)
							{
					
								System.out.println("Veuillez rentrer le nom");
								String n = kb.next();
								RechercheNom(fichier_p, n);
							}
						}
						else if ( a== 3)
						{
							boolean encore = true;
							while(encore)
								
							{
							System.out.println("Quelle est la personne morale concernée?");
							System.out.println("Veuillez saisir le numéro de téléphone");
							int numero_tel = Integer.parseInt(kb.next());
							System.out.println("Veuillez saisir l'élément actuel à modifier (Par ex : \"Durand\" ou \"010203040506\" : ");
							String current = kb.next();
							System.out.println("Veuillez saisir le nouvel élément: ");
							String modified = kb.next();
							Modifier(fichier_p, numero_tel, current, modified);
							System.out.println("Voulez-vous modifiier autre chose?");
							System.out.println("1. Oui");
							System.out.println("2. Non");
							System.out.println("Veuillez saisir le numéro correspondant à votre choix");
							int choix = Integer.parseInt(kb.next());
							if (choix==1)
							{
								encore=true;
							}
							else if(choix ==2)
							{
								encore = false;
							}
							else
							{
								boolean erreur = true;
								while (erreur)
								{
									System.out.println("L'entrée n'est pas acceptée, veuillez recommencer: ");
									int choix2 = Integer.parseInt(kb.next());
									if (choix2==1 || choix2==2)
										{
											erreur = false;
											if(choix2==2)
											{
												encore=false;
											}
										}
									
								}
								
								
							}
							}
						}
						else if ( a == 4)
						{
							List<List<String>> liste_membre = new ArrayList<>(Read(fichier_a));
							System.out.println("Quelle personne morale voulez-vous supprimer (Veuillez saisir son numéro de téléphone)");
							int num_tel = Integer.parseInt(kb.next());
							boolean nume =true;
							while(nume)
							{
								for (int c=0; c<liste_membre.size();c++)
								{
									if(Integer.parseInt(liste_membre.get(c).get(3))==num_tel)
									{
									Supprimer_Personne(fichier_a, num_tel);
									nume=false;
									}
									
								}
								if (nume==true)
								{
									System.out.println("Le numéro entré ne correspond à aucun adhérent, veuillez recommencer: ");
								}
							}
						}
							
				
			}
			}
			
			else if (num==2)
			{
				System.out.println();
				System.out.println("1. Proposer un don");
				System.out.println("2. Candidater pour un don");
				System.out.print("Entrez le numéro de la section choisie : ");

				boolean nombre2 = true;
				while (nombre2)
				{
				try {
					int num2 = Integer.parseInt(kb.next());
					nombre2 = false;
					if (num2==1) 
					{
						
						System.out.println();
						System.out.println("Quelles sont les caractéristiques du don ?");
						System.out.print("Date (sous la forme JJMMYEAR) : "); 
						
						date = Integer.parseInt(kb.next());
					
						System.out.println();
						System.out.println("Type de matériel : ");
						System.out.println("1. Mobilier Chambre"); 
						System.out.println("2. Mobilier Salle/Cuisine");
						System.out.print("Entrez le numéro de la section choisie : ");
						
						boolean nombre3 = true;
						while (nombre3)
							{
							try 
							{
								
								int num3 = Integer.parseInt(kb.next());
								nombre3 = false;
								Type_materiel = Mobilier.get(num3-1);
								if (num3==1) 
								{
									
									System.out.println();
									System.out.println("1. Matelas");
									System.out.println("2. Chevets");
									System.out.println("3. Armoires");
									System.out.println();
									System.out.print("Entrez le numéro correspondant au type : ");
									boolean nombre4bis = true;
									while (nombre4bis)
									{
										
										int num4bis = Integer.parseInt(kb.next());
										nombre4bis=false;
										nom=Mobilier_Chambre.get(num4bis);
									}
								}
								else if (num3==2)
								{	
			
									System.out.println();
									System.out.println("1. Table");
									System.out.println("2. Chaises");
									System.out.println("3. Electro-ménager");
									System.out.println("4. Cuisinière");
									System.out.println("5. Réfrigérateur");
									System.out.println("6. Lave-linge");
									System.out.println("7. Vaisselle");
									System.out.println("8. Couverts");
									System.out.println("9. Assiettes");
									System.out.print("Entrez le numéro correspondant au type : ");
									boolean nombre4 = true;
									while (nombre4)
									{
										try 
										{
											
											int num4 = Integer.parseInt(kb.next());
											nombre4 = false;
											nom=Mobilier_SC.get(num4);
											Type_materiel = Type_materiel +";"+ Mobilier_SC.get(num4-1);
											if (num4>9)
											{
												System.out.println();
												System.out.print("Le nombre entré n'est pas accepté, veuillez recommencer: ");
												nombre4 = true;
											}
													if (num4 == 1)
													{
														
														System.out.println();
														System.out.println("A quelle pièce est destinée la table? ");
														System.out.println("1. Cuisine");
														System.out.println("2. Salon");
														System.out.print("Entrez le numéro correspondant : ");
														boolean nombre5 = true;
														while (nombre5)
														{
															try 
															{
																
																int num5 = Integer.parseInt(kb.next());
																nombre5 = false;
																if (num5 != 1 && num5 != 2)
																{
																	System.out.print("Le nombre entré n'est pas accepté, veuillez recommencer: ");
																	nombre5 = true;
																}
																Type_materiel = Type_materiel +";"+ Type_Table.get(num5-1);
																System.out.println();
																System.out.println("De quelle forme est la table?");
																System.out.println("1. Rectangulaire");
																System.out.println("2. Carrée");
																System.out.println("3. Ronde");
																System.out.println("Entrer le numéro correspondant : ");
																boolean nombre6 = true;
																while (nombre6)
																{
																	try
																	{
																		
																		int num6 = Integer.parseInt(kb.next());
																		nombre6 = false;
																		if (num6 != 1 && num6 != 2 && num6 != 3)
																		{
																			System.out.print("Le nombre entré n'est pas accepté, veuillez recommencer: ");
																			nombre6 = true;
																		}
																		Type_materiel = Type_materiel +";"+ Forme_Table.get(num6-1);
																		
																	}
																	catch (NumberFormatException e)
																	{
																		System.out.print("L'entrée n'est pas un nombre, veuillez recommencer: ");
																	}
																}
															}
															catch (NumberFormatException e)
															{
																System.out.print("L'entrée n'est pas un nombre, veuillez recommencer: ");
															}
														}
													}
													else if (num4==7)
													{
														try
														{
														System.out.println();
														System.out.print("Entrer le nombre de vaisselles: ");
														
														int num7 = Integer.parseInt(kb.next());
														Type_materiel = Type_materiel + ";qte = " + num7;
														}
														catch (NumberFormatException e)
														{
															System.out.print("L'entrée n'est pas un nombre, veuillez recommencer: ");
														}
													
													}
													
										}
										catch (NumberFormatException e)
										{
											System.out.print("L'entrée n'est pas un nombre, veuillez recommencer: ");
										}
									}
									
								}
								else
								{
									System.out.print("Le nombre entré n'est pas accepté, veuillez recommencer: ");
									nombre3 = true;
								}
							}
							catch (NumberFormatException e)
							{
								System.out.print("L'entrée n'est pas un nombre, entrer un nombre : ");
								
							}
							}
					System.out.println("L'objet est-il volumineux? ");
					System.out.println("1. Oui");
					System.out.println("2. Non");
					System.out.println("Veuillez saisir le numéro correspondant à votre choix: ");
					int volume = Integer.parseInt(kb.next());
					description=Type_materiel+";Volumineux";
					if(volume==1)
					{
						
						System.out.print("Veuillez saisir la hauteur (en cm)");
						float hauteur = kb.nextFloat();
						System.out.println();
						System.out.print("Veuillez saisir la largeur (en cm)");
						float largeur = kb.nextFloat();
						System.out.println();
						System.out.print("Veuillez saisir la longueur (en cm)");
						float longueur = kb.nextFloat();
						System.out.println();
						description=description+";hauteur="+hauteur+";largeur="+largeur+";longueur="+longueur;
					}
					
					List<String> don_crée = new ArrayList<>();
					don_crée.add(String.valueOf(date));
					don_crée.add(nom);
					don_crée.add(String.valueOf(Read(archive_dons_proposes).size()+1));
					don_crée.add(utilisateur.get(1));
					don_crée.add(utilisateur.get(3));
					don_crée.add(utilisateur.get(2));
					don_crée.add(description);
					
					List<String> don_crée_bis = new ArrayList<>();
					for (String element : don_crée)
					{
						don_crée_bis.add(element);
					}
					don_crée_bis.set(2, String.valueOf(Read(archive_dons_en_traitement).size()+1));
					Ajout_Don(archive_dons_proposes, don_crée);
					Ajout_Don(archive_dons_en_traitement,don_crée_bis);
					
					}
					
					else if (num2==2)
					{
						if(utilisateur.contains("membre"))
						{
							System.out.println("L'accès à cet espace est réservé aux bénéficiaires, veuillez vous inscrire pour y accéder");
						}
						else
						{
							System.out.println("Quel mobilier désirez-vous?");
							System.out.println("Veuillez saisir la référence du don: ");
							
							String ref = kb.next();
							
							List<List<String>> liste_demande = new ArrayList<>(Read(archive_dons_stockes));
							for(int d=0;d<liste_demande.size();d++)
							{
							if(liste_demande.get(d).get(2)==ref)	
							{
								Ajout_Don(archive_dons_demandes,liste_demande.get(d));
								
							}
							
							}
							
						}
						
					}
					
				}
				catch (NumberFormatException e)
							{
								System.out.print("L'entrée n'est pas un nombre, entrer un nombre : ");
								
							}			
			}
				
			}
			else if (num==3)
			{
				System.out.println("1. Dons refusés");
				System.out.println("2. Dons en traitement");
				System.out.println("3. Dons vendus");
				System.out.println("4. Dons stockés");
				System.out.print("Veuillez saisir le numéro correspondant: ");
				int q = Integer.parseInt(kb.next());
				if (q==1)
				{
					System.out.println(Read(archive_dons_refuses));
				}
				else if(q==2)
				{
					if(utilisateur.contains("membre"))
					{
						System.out.println(Read(archive_dons_en_traitement));
						System.out.println("Souhaitez-vous traiter un don et changer son statut?");
						System.out.println("1. Oui");
						System.out.println("2. Non");
						System.out.println("Veuillez saisir le numéro correspondant à votre choix: ");
						int c = Integer.parseInt(kb.next());
						if (c==1)
						{
							System.out.println("Acceptez-vous le premier don (qui ne provient pas déjà de vous-même) parmi la liste des dons en traitement?");
							System.out.println("1.Oui, le don est validé");
							System.out.println("2. Non, le don est refusé");
							System.out.println("Veuillez saisir le numéro correspondant à votre choix: ");
							int v = Integer.parseInt(kb.next());
							List<List<String>> liste_traitement = new ArrayList<>(Read(archive_dons_en_traitement));
							 int x=0;
							 while(liste_traitement.get(x).get(2)==utilisateur.get(1))
							 {
								  x++;
							 }
							Supprimer_Don(archive_dons_en_traitement,liste_traitement.get(x).get(2));
							if (v==1)
							{
								liste_traitement.get(x).add(utilisateur.get(1));
								Ajout_Don(archive_dons_acceptes,liste_traitement.get(x));
								System.out.println("Où voulez-vous stocker l'objet?");
								System.out.println("1. Entrepot");
								System.out.println("2. Depot_Vente");
								System.out.println("Veuillez saisir le numéro correspondant à votre choix: ");
								int y=Integer.parseInt(kb.next());
								liste_traitement.get(x).add(Personne.get(y-1));
								System.out.println("Quelle est la date de réception prévue?");
								System.out.println("Veuillez saisir la date sous la forme JJMMYEAR");
								int recep = Integer.parseInt(kb.next());
								liste_traitement.get(x).add(String.valueOf(recep));
								
								if (y==2)
									 {
										 System.out.println("Quel est le montant prévu de l'objet?");
										 System.out.println("Veuillez saisir le montant: ");
										 montant=kb.nextFloat();
										 liste_traitement.get(x).add(String.valueOf(montant));
										 Ajout_Don(archive_dons_depot_vente,liste_traitement.get(x));
										 Ajout_Don(archive_dons_stockes,liste_traitement.get(x));
										 
										 
									 }
								else if(y==1)
								 	{
									 	Ajout_Don(archive_dons_entrepot, liste_traitement.get(x));
									 	Ajout_Don(archive_dons_stockes,liste_traitement.get(x));
									 	
								 	}
							}
							else if(v==2)
							{
								 Ajout_Don(archive_dons_refuses,liste_traitement.get(x));
								 
							}
						
						}
					}
					else
					{
						System.out.println("L'accès est restreint aux adhérents");
					}
				}
				else if(q==3)
				{
					System.out.println(Read(archive_dons_vendus));
				}
				else if(q==4)
				{
					System.out.println("Rechercher les dons stockés dans un :");
					System.out.println("1. Entrepot");
					System.out.println("2. Depot_vente");
					System.out.println("Veuillez saisir le numéro correspondant à votre choix");
					int ch = Integer.parseInt(kb.next());
					if(ch==1)
					{
						System.out.println(Read(archive_dons_entrepot));
						
					}
					else if(ch==2)
					{
						System.out.println(Read(archive_dons_depot_vente));
					}
					if(utilisateur.contains("membre"))
					{
						System.out.println("Voulez-vous traiter des demandes de don?");
						System.out.println("1. Oui");
						System.out.println("2. Non");
						System.out.print("Veuillez saisir le numéro correspondant à votre choix: ");
						int demand = Integer.parseInt(kb.next());
						if (demand==1)
						{
							
							System.out.println(Read(archive_dons_demandes));
							List<List<String>> treat = new ArrayList<>(Read(archive_dons_demandes));
							System.out.println("Acceptez-vous la première demande affichée (qui ne soit pas déjà la vôtre)?");
							System.out.println("1. Oui");
							System.out.println("2. Non");
							System.out.println("Veuillez saisir le numéro correspondant à votre choix: ");
							int response = Integer.parseInt(kb.next());
							int z=0;
							if (response==1)
							{
								
								while(treat.get(z).get(3)==utilisateur.get(1))
								{
									z++;
								}
								System.out.println("Veuillez saisir la date de retrait/vente (sous la forme JJMMYEAR) : ");
								int retrait = Integer.parseInt(kb.next());
					
								treat.get(z).add(String.valueOf(retrait));
								Supprimer_Don(archive_dons_stockes,Read(archive_dons_stockes).get(z).get(2));
								
								
								if(treat.get(z).contains("depot_vente"))
								{
									Ajout_Vente(archive_ventes,treat.get(z).get(3),Integer.parseInt(treat.get(z).get(10)));
									Ajout_Don(archive_dons_vendus,treat.get(z));
								}
							}
							
						}
					}
				}
			}
			else if (num==4)
			{
				System.out.println("1. Nombre de propositions de dons reçues");
				System.out.println("2. Nombre de donateurs/bénéficiaires");
				System.out.println("3. Nombre de propositions de dons acceptées et ratio reçues/acceptées pour les objets volumineux");
				System.out.println("4. Nombre de ventes suivant les destinations possibles");
				System.out.println("5. Principale catégorie d'article en stock");
				System.out.println("6. Moyenne de temps entre date de réception et date de retrait des dons dans les zones de stockage");
				System.out.println("7. Moyenne de prix des objets dans les dépôts-ventes");
				System.out.println("8. Moyenne d'âge des bénéficiaires");
				System.out.println("Veuillez saisir le numéro correspondant à votre choix: ");
				int cp = Integer.parseInt(kb.next());
				if(cp==1)
				{
					System.out.println(Nombre_Dons(archive_dons_proposes)+" propositions de dons reçues");
				}
				else if(cp==2)
				{
					System.out.println(Nombre_Dons(fichier_a)+" adhérents");
					System.out.println(Nombre_Dons(fichier_b)+" bénéficiaires");
				}
				else if(cp==3)
				{
					List<List<String>> liste_acceptes = new ArrayList<>(Read(archive_dons_acceptes));
					List<List<String>> liste_recus = new ArrayList<>(Read(archive_dons_proposes));
					for(List<String> donation : liste_acceptes)
					{
						if (donation.get(6).indexOf("Volumineux")!=-1)
						{
							Ajout_Don(archive_dons_volumineux_acceptes,donation);
						}
					}
					for(List<String> donation : liste_recus)
					{
						if (donation.get(6).indexOf("Volumineux")!=-1)
						{
							Ajout_Don(archive_dons_volumineux_recus,donation);
						}
					}
					
					System.out.println(Nombre_Dons(archive_dons_acceptes)+" propositions de dons acceptées");
					
					int ratio = Nombre_Dons(archive_dons_volumineux_recus)/Nombre_Dons(archive_dons_volumineux_acceptes);
					System.out.println("Sur l'ensemble des propositions de dons d'objet volumieux, "+ratio+"% ont été acceptés");
				}
				else if(cp==4)
				{
					List<List<String>> Ventes = new ArrayList<>(Read(archive_ventes));
					
					for(int a=0;a<Read(archive_ventes).size();a++)
					{	int nombre_ventes=1;
						for(int b=0;b<Read(archive_ventes).size()&&b!=a;b++)
						{
							if(Ventes.get(a).get(1)==Ventes.get(b).get(1))
							{
								nombre_ventes++;
							}
						}
						System.out.println(Ventes.get(a).get(1)+" : "+nombre_ventes);
					}
					
				}
				else if(cp==5)
				{
					List<List<String>> liste_stock = new ArrayList<>(Read(archive_dons_stockes));
					List<List<String>> occurence = new ArrayList<>();
					List<String> Noms = new ArrayList<>();
					
					for(int j=0;j<Mobilier.size();j++)
						{
						for (List<String> don : liste_stock)
						{
							if(Mobilier.get(j)==don.get(6))
							{
								Noms.add(don.get(6));
							}
						}
						List<String> copy_noms = new ArrayList<>(Noms);
						occurence.add(copy_noms);
						Noms.clear();
						}
					int max=occurence.get(0).size();
					int ref_nom=0;
					for (int n=0;n<occurence.size()-1;n++)
					{
						if(occurence.get(n).size()<occurence.get(n+1).size())
						{
						max=occurence.get(n+1).size();
						ref_nom=n+1;
						}
					}
					System.out.println("L'association a principalement vendu/donné des mobiliers de type " +occurence.get(ref_nom).get(0)+ "au nombre de "+max);
				}
				else if(cp==6)
				{
					
					
					List<List<String>> liste_depot = new ArrayList<>(Read(archive_dons_stockes));
		
					for(int a=0;a<liste_depot.size();a++)
					{
						int moyenne_jour=0;
						int moyenne_mois=0;
						int moyenne_annee=0;
						
						for(int b=0;b<liste_depot.size();b++)
						{
							
							if(liste_depot.get(a).get(8)==liste_depot.get(b).get(8))
							{
								
								int jour = Integer.valueOf(liste_depot.get(b).get(9).substring(0, 2))-Integer.valueOf(liste_depot.get(b).get(1).substring(0,2));
								int mois = Integer.valueOf(liste_depot.get(b).get(9).substring(2,4))-Integer.valueOf(liste_depot.get(b).get(1).substring(2,4));
								int year = Integer.valueOf(liste_depot.get(b).get(9).substring(4,8))-Integer.valueOf(liste_depot.get(b).get(1).substring(4,8));
								
								moyenne_jour+=jour;
								moyenne_mois+=mois;
								moyenne_annee+=year;
							}
						}
					moyenne_jour=moyenne_jour/liste_depot.size();
					moyenne_mois=moyenne_mois/liste_depot.size();
					moyenne_annee=moyenne_annee/liste_depot.size();
					System.out.println("La zone de stockage "+liste_depot.get(a).get(8)+" a une moyenne de délai d'environ "+moyenne_jour+" jours "+moyenne_mois+" mois et "+moyenne_annee+" années" );
					
						
					}
					
				}
				
				
			
			else
			{
				System.out.println("Le chiffre entré n'est pas accepté, veuillez recommencer: ");
				nombre = true;
			}
			}
		}
		
		catch (NumberFormatException e)
		{
			System.out.print("L'entrée n'est pas un nombre, entrer un nombre : ");
			
		}
		
		}
		
		System.out.println();
		System.out.println("Souhaitez-vous continuer à faire des opérations avec ce compte? (Pour changer d'utilisateur, veuillez ré-exécuter le programme)");
		System.out.println("1. Oui");
		System.out.println("2. Non");
		System.out.print("Veuillez saisir le numéro correspondant à votre choix: ");
		int out = Integer.parseInt(kb.next());
		if(out==2)
		{
			keep_going=false;
		}	

	}
		//kb.close();
		
	}
	
}
