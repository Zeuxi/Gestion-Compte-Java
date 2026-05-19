import java.io.*;
import java.util.Vector;
class compte {

        private String nom;
        private int id;
        private float solde;

        static Vector<compte> ListeComptes = new Vector<>();
        BufferedReader saisie = new BufferedReader(new InputStreamReader(System.in));


        public compte(String name,int identifiant,float solde){
            nom=name;
            id=identifiant;
            this.solde=solde;
        }
        public compte(){}
        public void clear(){
            try{ new ProcessBuilder( "cmd","/c","cls").inheritIO().start().waitFor();new ProcessBuilder("clear").inheritIO().start().waitFor();}catch(IOException | InterruptedException e){}
        }



        public String getNom() {
            return nom;
        }
        public int getId() {
            return id;
        }
        public float getSolde() {return solde;}
        public void setSolde(float montant){
            solde=montant;
        }
        public void creerCompte()throws IOException{
            String nomTitulaire;
            int num;
            float montant;
            byte nombre;
            boolean verifId=false;
            clear();
            System.out.println("\n\n\n\t\t\t_* 1-Creation compte client");
            System.out.print("\n\n\n\t\tCombien de compte voullez-vous creer : ");
            nombre = Byte.parseByte(saisie.readLine());
            for(int i=0;i<nombre;i++){
                clear();
                System.out.println("\n\n\n\t\t\t_* 1-Creation compte client");
                System.out.println("\n\n\n\t\t\t\t\tCompte " + (i+1));
                System.out.print("\n\n\n\t\tVeuillez entrer le nom complet du titulaire du compte : ");
                nomTitulaire=saisie.readLine().toUpperCase();
                while(nomTitulaire.contentEquals("ADMIN")){
                    clear();
                    System.out.println("\n\n\n\t\t\t\t\tCompte " + (i+1));
                    System.out.print("\n\n\n\t\tLe nom du titulaire du compte ne peut etre \"ADMIN\" Reesayez : ");
                    nomTitulaire=saisie.readLine().toUpperCase();
                }
                System.out.print("\n\n\t\tVeuillez entrer l'identifiant (numero) du titulaire : ");
                num=Integer.parseInt(saisie.readLine());
                while(num==0){
                    clear();
                    System.out.println("\n\n\n\t\t\t\t\tCompte " + (i+1));
                    System.out.print("\n\n\n\t\tLe numero du titulaire du compte ne peut etre \""+0+"\" Reesayez : ");
                    num=Integer.parseInt(saisie.readLine());
                }
                do {
                    for(compte C : ListeComptes){
                        if(C.getId()==num ){
                            verifId=true;
                        }else{
                            verifId=false;
                        }
                    }if (num==0) {
                        verifId=true;
                    }
                    if(verifId){
                        System.out.println("\n\n\n\t\t\t\t\tCompte " + (i+1));
                        System.out.print("\n\n\n\t\tNom lie au compte : \" "+nomTitulaire+" \"");
                        System.out.print("\n\n\n\t\tUn compte est deja lie a l'identifiant \" "+num+" \" Reesayez un nouvel identifiant: ");
                        num=Integer.parseInt(saisie.readLine());
                    }
                } while (verifId);
                
                System.out.print("\n\n\t\tVeuillez entrer la somme initiale a recharger au compte de \""+nomTitulaire+"\" : ");
                montant=Float.parseFloat(saisie.readLine());
                clear();
                compte interim=new compte(nomTitulaire,num,montant);
                ListeComptes.add(interim);
                System.out.println("\n\n\t\t\t\tOperation\n\n\n\t\tNouveau compte au nom de \" "+nomTitulaire+" \" Identifiant : "+num+" cree avec succes");
                System.out.print("\n\n\n\t\t\t\tAppuyez sur \"entrer\" pour continuer......");
                saisie.readLine();
            }
        }
        public void creerAdmin(){
            compte admin =new compte("ADMIN",00000000,0);
            ListeComptes.add(admin);
        }
        public void RechargeSolde() throws IOException {
            int numero; float montant;
            byte choix;
            boolean verif=false;
            compte beneficiaire=new compte();
            clear();
            System.out.println("\n\n\n\t\t\t_* 3-Recharge de compte client");
            System.out.print("\n\n\t\tVeuillez entrer l'identifiant (numero) du compte a recharger : ");
            numero=Integer.parseInt(saisie.readLine());
            do {
                for (compte pas : ListeComptes){
                    if (numero== pas.getId()){
                        verif=true;
                        beneficiaire=pas;
                    }
                }if (!verif) {
                    clear();
                    System.out.print("\n\n\n\t\t\tL'identifiant" + numero + " n'existe pas dans la liste. Reesayez : ");
                    numero = Integer.parseInt(saisie.readLine());
                }
            }while (!verif);

            System.out.print("\n\n\t\tVeuillez entrer la somme a recharger au compte de \" "+beneficiaire.getNom()+" \" ID : \" "+beneficiaire.getId()+" \" : ");
            montant=Float.parseFloat(saisie.readLine());
            clear();
            for (compte parcours : ListeComptes) {
                if (numero==parcours.getId()) {
                    System.out.print("\n\n\n\t\t\tConfirmez la recharge de " + montant + " Fcfa au numero \" " + parcours.getId() + " \" Recepteur : \"  " + parcours.getNom() + " \" (1-oui/2-non) : ");
                    choix = Byte.parseByte(saisie.readLine());
                    while (choix < 1 || choix > 2) {
                        System.out.print("\n\n\n\t\t\tIl n'existe pas d'option " + choix + ". Reessayez :");
                        choix = Byte.parseByte(saisie.readLine());
                    }
                    if (choix == 1) {
                        clear();
                        parcours.solde+=montant;
                        System.out.print("\n\n\n\t\t\tRecharge de " + montant + " Fcfa effectuee avec succes au compte de \" " + parcours.getNom() + " \" ID : \" +226 " + parcours.getId() + " \" par l'operateur Admin...");
                    } else {
                        System.out.println("\n\n\n\t\t\tAnnulation de l'operation confimee....");
                    }
                }
            }

        }

        public compte migration() throws IOException {
            int numero;
            byte v=3;
            clear();
            System.out.print("\n\n\n\t\t\t_* 2-Connexion au client.... ");
            System.out.print("\n\n\n\t\t\tVeuillez entrer votre identifiant de compte (numero) : ");
            numero = Integer.parseInt(saisie.readLine());
            do {
                for (compte parcours : ListeComptes) {
                    if (numero == parcours.getId()) {
                        clear();
                        System.out.println("\n\n\n\t\t\tConnexion au compte associe au numero \"+226 " + numero + " \" \n\n\n\t\tBienvenu(e) : \"" + parcours.getNom() + " \"");
                        System.out.println("\n\n\n\t\t\tAppuyez \" entrer \" pour continuer........");
                        saisie.read();

                        return parcours;
                    }

                }clear();
                System.out.println("\n\n\n\t\t\tLe compte associe au numero \"+226 " + numero + " \" est introuvable.....Reesayez("+v+" essais resatants) : ");
                numero = Integer.parseInt(saisie.readLine());
                v--;

            }while (v>0);
            return new compte();//dervra retouner un type comte avec un constructeur qui va affecter à l'attribut id 404 (not found) et on s'en servira plus atrd !!!!!!Ne pas oublier de creer des constructeurs

        }

        public void tranfert(compte Compteactuel /*compteactuel contiendra ce que va retouner migration() qui correspond au compte client actif a l'instant T*/) throws IOException {
            clear();
            float montantTransferer;
            int numeroRecepeteur;
            System.out.println("\n\n\n\t\t\t_* 2-Transferer de l'argent ");
            System.out.print("\n\n\t\tEntrez le numero du Destinataire : ");
            numeroRecepeteur = Integer.parseInt(saisie.readLine());
            while (numeroRecepeteur == Compteactuel.getId()) {
                clear();
                System.out.print("\n\n\n\t\t\tOperation Impossible ,l'envoyeur et le destinataire ne peuvent pas etre la meme personne; Reesayez :");
                numeroRecepeteur = Integer.parseInt(saisie.readLine());
            }
            while(numeroRecepeteur==0){
                System.out.print("\n\n\n\t\t\tIl n'est pas possible que l'expediteur soit l'ADMINISTRATEUR ID : \" " + numeroRecepeteur + " \" Reesayez : ");
                numeroRecepeteur = Integer.parseInt(saisie.readLine());
            }
            int verif=0;
            do{
                for(compte v:ListeComptes){
                    if(v.getId()==numeroRecepeteur){
                        verif=1;
                    }
                }
                if(verif==0){
                    System.out.println("\n\n\n\t\t\tLe compte associe au numero \" " + numeroRecepeteur + " \" est introuvable.....Reesayez : ");
                    numeroRecepeteur = Integer.parseInt(saisie.readLine());
                }
            }while(verif==0);

            System.out.print("\n\n\t\tEntrez le montant a Transferer : ");
            montantTransferer = Float.parseFloat(saisie.readLine());
            montantTransferer+=montantTransferer/100;//correspond a l'ajout des 1% de taxe
            while (montantTransferer > Compteactuel.getSolde()) {
                clear();
                System.out.print("\n\n\n\t\t\tVotre solde est insuffisant pour effectuer cette operation (Solde actuel : " + Compteactuel.getSolde() + " Fcfa). Reesayez : ");
                montantTransferer = Float.parseFloat(saisie.readLine());
                montantTransferer+=montantTransferer/100;//correspond a l'ajout des 1% de taxe
            }
            
            for (compte parcours : ListeComptes){
                if(parcours.getId()==Compteactuel.getId()){
                    parcours.setSolde(parcours.getSolde()-montantTransferer);
                }
                if(parcours.getNom().contentEquals("ADMIN")){
                    parcours.setSolde(parcours.getSolde()+(montantTransferer/101));
                }
                if(parcours.getId()==numeroRecepeteur){
                    parcours.setSolde(parcours.getSolde()+(montantTransferer-(montantTransferer/101)));
                    clear();
                    System.out.println("\n\n\t\tLe transfert de " + montantTransferer + " Fcfa au numero \" +226 " + numeroRecepeteur + " \" Recepteur : \"  " + parcours.getNom() + " \" a ete effectuee\n\n\n\t\tFrais : "+montantTransferer/101+" Fcfa");
                    System.out.println("\n\n\t\tVotre solde actuel est de " + Compteactuel.getSolde()+" Fcfa");
                    saisie.readLine();
                }else {
                    clear();
                    System.out.println("\n\n\n\t\t\tLe compte associe au numero \"+226 " + numeroRecepeteur + " \" est introuvable.....");
                }
            }
        }

        public void voirMonSolde(compte Compteactuel) throws IOException {
            clear();
            boolean vf=false;
            compte ncmpt = new  compte();
            System.out.println("\n\n\n\t\t\t_* 1-Voir mon solde ");
            for (compte parcours : ListeComptes) {
                if (parcours.getId() == Compteactuel.getId()) {
                    ncmpt=parcours;
                    saisie.readLine();
                }
            }
            System.out.print("\n\n\n\t\t\tNom du compte : \" "+ncmpt.getNom()+" \"  ID : \" "+ncmpt.getId()+" \" solde actuel est de " + ncmpt.getSolde() + " Fcfa");
        }

        public void VoirSoldeClient() throws IOException {
            
            clear();
            System.out.println("\n\n\n\t\t\t_* 2-Affichage du solde d'un compte client");
            if (ListeComptes.size()<=1) {
                System.out.print("\n\n\n\t\t\tLa liste ne contient aucun client pour le moment........");
                
            }else{
                int numeroClient;
                System.out.print("\n\n\t\tEntrez le numéro du Client : ");
                numeroClient = Integer.parseInt(saisie.readLine());
                clear();
                for (compte parcours : ListeComptes) {
                    if (numeroClient == parcours.getId()) {
                        System.out.println("\n\n\n\n\n\n\t\t\tID : \" "+parcours.getId()+" \"  Client \"" + parcours.getNom() + " \" Solde : " + parcours.getSolde() + " Fcfa");
                    }
                }
            }
            
        }

        public void afficherUnique() throws IOException{
            clear();
            System.out.println("\n\n\n\t\t\t_* 5-Affichage des informations d'un compte client");
            if (ListeComptes.size()<=1) {
                System.out.print("\n\n\n\t\t\tLa liste ne contient aucun client pour le moment........");
                
            }else{
            int numeroClient;
            boolean check=false;
            compte van=new compte();
            System.out.print("\n\n\n\t\t\tEntrez le numéro du Client : ");
            numeroClient = Integer.parseInt(saisie.readLine());
            for (compte parcours : ListeComptes) {
                if (numeroClient == parcours.getId()) {
                    check=true;
                    van=parcours;
                }
            }
            if (check){
                System.out.println("\n\n\n\t\t\tNom Titulaire : " +van.getNom() + "\n\t\t\tID(Numero) Titulaire : " +van.getId() + "\n\t\t\tSolde : " + van.getSolde() + "Fcfa\n");
            }else{
                System.out.println("\n\n\n\t\t\tLe compte associe au numero \" " + numeroClient + " \" est introuvable.....");
            }
            }

        }

        public void afficherUtilisateurs() {
            clear();
            System.out.println("\n\n\n\t\t\t_* 6-Affichage de la liste des comptes client");
            for (compte parcours : ListeComptes) {
                System.out.println("\n\n\t\tNom Titulaire : " + parcours.getNom() + "\n\t\tID(Numero) Titulaire : " + parcours.getId() + "\n\t\tSolde : " + parcours.getSolde()+ "Fcfa\n");
            }
        }

        public void transfertAdmin() throws IOException {
            clear();
            System.out.println("\n\n\n\t\t\t_* 4- Transfert d'argent client client");
            int numeroDestinataire;
            int numeroEmetteur;
            float montant;
            boolean check=false;
            boolean error=true;
            System.out.print("\n\n\n\t\t\tVeuillez entrer le numero de l'expediteur : ");
            numeroEmetteur = Integer.parseInt(saisie.readLine());
            while (numeroEmetteur==0) {
                clear();
                System.out.println("\n\n\n\t\t\t_* 4- Transfert d'argent client client");
                System.out.print("\n\n\n\t\t\tIl n'est pas possible que l'expediteur soit l'ADMINISTRATEUR ID : \" " + numeroEmetteur + " \" Reesayez : ");
                numeroEmetteur = Integer.parseInt(saisie.readLine());
            }
            for (compte pas : ListeComptes){
                if(numeroEmetteur== pas.getId()){
                    check=true;
                }
            }
            
            while (!check){
                clear();
                System.out.println("\n\n\n\t\t\t_* 4- Transfert d'argent client client");
                System.out.print("\n\n\n\t\t\tL'identifiant (numero) client " + numeroEmetteur + " est intoruvable reesayez : ");
                numeroEmetteur = Integer.parseInt(saisie.readLine());
                for (compte pas : ListeComptes){
                    if(numeroEmetteur== pas.getId()){
                        check=true;
                    }
                }
                if(numeroEmetteur==0){
                    clear();
                    System.out.println("\n\n\n\t\t\t_* 4- Transfert d'argent client client");
                    System.out.print("\n\n\n\t\t\tIl n'est pas possible que l'expediteur soit l'ADMINISTRATEUR ID : \" " + numeroEmetteur + " \" Reesayez : ");
                    numeroEmetteur = Integer.parseInt(saisie.readLine());
                    check=false;
                }
            }

            check=false;
            System.out.print("\n\n\n\t\t\tVeuillez entrer le numero du Destinataire : ");
            numeroDestinataire = Integer.parseInt(saisie.readLine());
            while (numeroDestinataire==0) {
                clear();
                System.out.println("\n\n\n\t\t\t_* 4- Transfert d'argent client client");
                System.out.print("\n\n\n\t\t\tIl n'est pas possible que le destinataire soit l'ADMINISTRATEUR ID : \" " + numeroEmetteur + " \" Reesayez : ");
                numeroDestinataire = Integer.parseInt(saisie.readLine());
            }
            for (compte pas : ListeComptes) {
                if (pas.getId() == numeroDestinataire) {
                    check=true;
                }
            }
            while (check==false){
                clear();
                System.out.println("\n\n\n\t\t\t_* 4- Transfert d'argent client client");
                System.out.println("\n\n\n\t\t\t_* Numero identifiant du client expediteur : \" "+numeroEmetteur+" \"");
                System.out.print("\n\n\n\t\t\tL'identifiant (numero) +226 " + numeroDestinataire + " est intoruvable reesayez : ");
                numeroDestinataire = Integer.parseInt(saisie.readLine());
                for (compte pas : ListeComptes){
                    if(numeroDestinataire== pas.getId()){
                        check=true;
                    }
                }
                if(numeroDestinataire==0){
                    clear();
                    System.out.println("\n\n\n\t\t\t_* 4- Transfert d'argent client client");
                    System.out.print("\n\n\n\t\t\tIl n'est pas possible que le destinataire soit l'ADMINISTRATEUR ID : \" " + numeroEmetteur + " \" Reesayez : ");
                    numeroDestinataire = Integer.parseInt(saisie.readLine());
                    check=false;
                }
            }
            check=true;
            while (numeroEmetteur == numeroDestinataire && check) {
                clear();
                if(error){
                    System.out.print("\n\n\n\t\t\tOperation Impossible ,l'envoyeur et le destinataire ne peuvent pas etre la meme personne.....\" Appuyez sur entrer pour continuer\"");
                    saisie.readLine();
                    clear();
                    System.out.println("\n\n\n\t\t\t_* 4- Transfert d'argent client client");
                    System.out.println("\n\n\n\t\t\t_* Numero identifiant du client expediteur : \" "+numeroEmetteur+" \"");
                    System.out.print("\n\n\n\t\t\tEntrez de nouveau le numero du Destinataire : ");
                    numeroDestinataire = Integer.parseInt(saisie.readLine());
                }else{
                    System.out.print("\n\n\n\t\t\tOperation impossible l'identifiant (numero) +226 " + numeroDestinataire + " est intoruvable.....\" Appuyez sur entrer pour continuer\" : ");
                    saisie.readLine();
                    clear();
                    System.out.println("\n\n\n\t\t\t_* 4- Transfert d'argent client client");
                    System.out.println("\n\n\n\t\t\t_* Numero identifiant du client expediteur : \" "+numeroEmetteur+" \"");
                    System.out.print("\n\n\n\t\t\tEntrez de nouveau le numero du Destinataire : ");
                    numeroDestinataire = Integer.parseInt(saisie.readLine());
                }
                for (compte pas : ListeComptes){
                    if (numeroDestinataire== pas.getId()){
                        check=false;
                    }
                }
                if (numeroDestinataire==numeroEmetteur){
                    error=true;
                }else{
                    error=false;
                }
            }
            clear();
            System.out.println("\n\n\n\t\t\t_* Numero identifiant du client expediteur : \" "+numeroEmetteur+" \"");
            System.out.println("\n\n\n\t\t\t_* Numero identifiant du client destinataire : \" "+numeroDestinataire+" \"");
            System.out.print("\n\n\n\t\t\tEntrez le montant a Transferer : ");
            montant = Float.parseFloat(saisie.readLine());
            montant+=montant/100;
            //_______________________________________________________________________________________________________________
            check=true;
            compte tie=new compte();
            do{
                for(compte pas :ListeComptes){
                    if(pas.getId()==numeroEmetteur){
                        tie=pas;
                        if(pas.getSolde()<montant){
                            check=false;
                        }else{
                            check=true;
                        }
                    }
                }
                if (!check){
                    clear();
                    System.out.print("\n\n\n\t\t\tLe solde de l'envoyeur est insuffisant pour effectuer cette operation (Solde actuel : " + tie.getSolde() + " Fcfa). Reesayez : ");
                    montant = Float.parseFloat(saisie.readLine());
                    montant+=montant/100;
                }
            }while(!check);

            //________________________________________________________________________________________________________________
            /**/
            for (compte pas : ListeComptes) {
                if (pas.getId() == numeroEmetteur) {
                    pas.setSolde(pas.getSolde()-montant);
                }
                if (pas.getNom().contentEquals("ADMIN")) {
                    pas.setSolde(pas.getSolde()+(montant/101));
                }
                if (pas.getId() == numeroDestinataire) {
                    pas.setSolde(pas.getSolde()+(montant-(montant/101)));
                    clear();
                    System.out.println("\n\n\n\n\t\t\t\tOperation Reussie\n\n\n\t\t\t\tTransfert de "+montant+" fcfa de \" "+tie.getNom()+" \" Identifiant : \" "+tie.getId()+" \" vers \" "+pas.getNom()+" \" Identifiant : \" "+pas.getId()+" \"");
                }
            }

        }
        public String deconnexion()throws IOException{
            clear();
            System.out.println("\n\n\n\t\t\t_*- Deconnexion......");
            System.out.println("\n\n\n\t\t_Tapez sur \"entrer pour continuer .............\"");
            saisie.readLine();
            return "2";

        }
        public byte noClient(byte indice)throws IOException{
            if(indice==2){
                if(ListeComptes.size()<=1){
                clear();
                System.out.print("\n\n\n\t\t\tLa liste ne contient aucun client pour le moment........Tapez sur \"entrer pour continuer .............\"");
                saisie.read();
                return 3;
            }
            }
            return indice;
        }
    }

public class GestCompte{
    BufferedReader saisie = new BufferedReader(new InputStreamReader(System.in));




    //__________________________________________________________________________________________________________________________________________
        public void clear(){
            try{ new ProcessBuilder( "clear").inheritIO().start().waitFor();}catch(IOException | InterruptedException e){}
            try{ new ProcessBuilder( "cmd","/c","cls").inheritIO().start().waitFor();}catch(IOException | InterruptedException e){}

        }
        public byte menuClient() throws IOException {
            clear();
            System.out.println("\t\t\t_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
            System.out.println("\n\t\t\t\t\t MENU(Client)");
            System.out.println("\t\t\t_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
            System.out.println("\t\t\t*_ 1-Transferer de l'argent");
            System.out.println("\t\t\t*_ 2-Voir mon solde");
            System.out.println("\t\t\t*_ Tapez sur (*_ 0 pour quitter ou sur *_ 3 pour rejoindre le menu des connexions) ");
            System.out.print("\n\nSelectionnez une Option : ");
            byte choix=Byte.parseByte(saisie.readLine());
            while (choix>3 || choix<0){
                System.out.print("\n\n\nl'option ne fait pas partie de la liste : ");
                choix=Byte.parseByte(saisie.readLine());
            }
            return choix;
        }
        public byte menuConnexion() throws IOException {
            clear();
            System.out.println("\t\t\t_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
            System.out.println("\n\t\t\t\t\t MENU(Connexion)");
            System.out.println("\t\t\t_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
            System.out.println("\n\t\t\t*_ 1-Se connecter en tant qu'Administrateur");
            System.out.println("\n\t\t\t*_ 2-Se connecter en tant que client");
            System.out.print("\n\nSelectionnez une Option : ");
            byte choix=Byte.parseByte(saisie.readLine());
            while (choix>2 || choix<1){
                System.out.print("\n\n\nl'option ne fait pas partie de la liste : ");
                choix=Byte.parseByte(saisie.readLine());
            }
            return choix;
        }
        public byte menuAdmin() throws IOException {
            System.out.println("\t\t\t_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
            System.out.println("\n\t\t\t\t\t\t MENU(Admin)");
            System.out.println("\t\t\t_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
            System.out.println("\n\t\t\t\t*_ 1-Creer un compte client");
            System.out.println("\n\t\t\t\t*_ 2-Afficher le solde d'un client");
            System.out.println("\n\t\t\t\t*_ 3-Recharger le compte d'un client");
            System.out.println("\n\t\t\t\t*_ 4-Transfert d'argent client a client");
            System.out.println("\n\t\t\t\t*_ 5-Afficher les informations d'un client");
            System.out.println("\n\t\t\t\t*_ 6-Afficher la liste des utilisateurs");
            System.out.println("\n\t\t\t\t*_ 7-Deconnexion");
            System.out.println("\n\t\t\t\t*_ 8-Quittez");
            System.out.print("\n\nSelectionnez une Option : ");
            byte choix=Byte.parseByte(saisie.readLine());
            while (choix>8 || choix<1){
                System.out.print("\n\n\nl'option ne fait pas partie de la liste : ");
                choix=Byte.parseByte(saisie.readLine());
            }
            return choix;
        }
    //_______________________________________________________________________________________________________________________________________

    public static void main(String [] args)throws IOException {
            final String password = "MOT_DE_PASSE";
            final String exit = "2";
            GestCompte e  = new GestCompte();
            String Ipasswd=exit;
            byte choixConnexion;
            byte continuer=0;
            compte cobaye=new compte();
            compte intermediaire=new compte();
            cobaye.creerAdmin();
            BufferedReader saisie = new BufferedReader(new InputStreamReader(System.in));
            do {
                e.clear();
                System.out.println("Bienevenu(e) a l'application de portefeuille electronique... \n\n\n");
                choixConnexion=e.menuConnexion();
                choixConnexion=cobaye.noClient(choixConnexion);

                switch (choixConnexion){
                    case 1:
                        do {
                            e.clear();
                            System.out.println();
                            System.out.print("\n\n\t\t\t\tBienvenu Adminisatreur....\n\n\n\t\t\tVeuillez entrer votre mot de passe :");
                            Ipasswd=saisie.readLine().toUpperCase();
                            while (!Ipasswd.contentEquals(password) && !Ipasswd.contentEquals(exit)){
                                e.clear();
                                System.out.print("\n\n\n\t\t\tLe mot de passe saisi est incorrecte. Reesayez (ou Entrez 2 pour revenir au menu de connexion):");
                                Ipasswd=saisie.readLine().toUpperCase();
                            }
                        }while (!Ipasswd.contentEquals(password) && !Ipasswd.contentEquals(exit));
                        if (Ipasswd.contentEquals(exit)){
                            continuer=2;
                            break;
                        }
                        if (Ipasswd.contentEquals(password)) {
                            do {
                                e.clear();

                                byte choixAdmin = e.menuAdmin();
                                switch (choixAdmin) {

                                    case 1:
                                        cobaye.creerCompte();
                                        System.out.print("\n\n\t\t\tContinuer(1-oui/0-non) : ");
                                        continuer = Byte.parseByte(saisie.readLine());
                                        break;
                                    case 2:
                                        cobaye.VoirSoldeClient();
                                        System.out.print("\n\n\t\t\tContinuer(1-oui/0-non) : ");
                                        continuer = Byte.parseByte(saisie.readLine());
                                        break;
                                    case 3:
                                        cobaye.RechargeSolde();
                                        System.out.print("\n\n\t\t\tContinuer(1-oui/0-non) : ");
                                        continuer = Byte.parseByte(saisie.readLine());
                                        break;
                                    case 4:
                                        cobaye.transfertAdmin();
                                        System.out.print("\n\n\t\t\tContinuer(1-oui/0-non) : ");
                                        continuer = Byte.parseByte(saisie.readLine());
                                        break;
                                    case 5:
                                        cobaye.afficherUnique();
                                        System.out.print("\n\n\t\t\tContinuer(1-oui/0-non) : ");
                                        continuer = Byte.parseByte(saisie.readLine());
                                        break;
                                    case 6:
                                        cobaye.afficherUtilisateurs();
                                        System.out.print("\n\n\t\t\tContinuer(1-oui/0-non) : ");
                                        continuer = Byte.parseByte(saisie.readLine());
                                        break;
                                    case 7:
                                        Ipasswd=cobaye.deconnexion();
                                        continuer=2;
                                        break;
                                    case 8:
                                        e.clear();
                                        System.out.println("\t\t\t*_ 8-Quittez");
                                        continuer=2;
                                        break;
                                }


                            } while (continuer == 1);
                        }

                        break;
                    case 2:
                        e.clear();
                        cobaye=intermediaire.migration();
                        do {
                            choixConnexion=e.menuClient();
                            switch (choixConnexion){
                                case 0:
                                    e.clear();
                                    System.out.println("\t\t\t*_Quittez");
                                    Ipasswd="sortie";
                                    break;
                                case 1:
                                    cobaye.tranfert(cobaye);
                                    Ipasswd="sorti";
                                    break;
                                case 2:
                                    intermediaire.voirMonSolde(cobaye);
                                    Ipasswd="sorti";
                                    break;
                                case 3:
                                    Ipasswd=exit;
                                    break;
                            }
                        } while (Ipasswd.contentEquals("sorti"));

                    break;
                }
            }while(Ipasswd.contentEquals(exit));

        }
}

