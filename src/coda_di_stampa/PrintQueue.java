package coda_di_stampa;

import java.io.File;

public class PrintQueue {

    private Node head;
    private Node tmp;
    private Node p;
    private Node s;

    public PrintQueue(){
        head = null;
    }

    public void enqueue(String contenuto){
        Node newNode = new Node(contenuto, head);
        head = newNode;
    }

    /*public void inserimentoInCoda(Invitato invitato){
        tmp = head;

        while(tmp.getLink() != null){
            tmp = tmp.getLink();
        }
        
        Invitato invitatoClonato;
        try {
            invitatoClonato = (Invitato) invitato.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println("Errore");
            return;
        }
        Node nuovoNodo = new Node(invitatoClonato, null);
        tmp.setLink(nuovoNodo);
    }*/
    
    /*public void add(Invitato invitato, int posizione){
        if(posizione == 1) {
            enqueue(invitato);
            return;
        }
        
        if(posizione > count()){
            inserimentoInCoda(invitato);
            return;
        }

        if(posizione < 1){
            System.out.println("Inserimento fallito (posizione < 1)");
            return;
        }
        
        p = head;
        s = head.getLink();

        while(posizione - 2 > 0){ // presuppongo che le posizioni partano da 1
            posizione--;
            
            p = s;
            s = s.getLink();
        }

        Invitato invitatoClonato;
        try {
            invitatoClonato = (Invitato) invitato.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println("Errore");
            return;
        }
        
        Node newNode = new Node(invitatoClonato, s);
        p.setLink(newNode);
    }*/

    public String rimozioneInTesta(){
        if(head == null){ // oppure count() == 0
            System.out.println("La lista è vuota");
            return null;
        }
        
        Node nodoDaRimuovere = head;
        head = nodoDaRimuovere.getLink();
        return nodoDaRimuovere.getInfo();
    }

    public String dequeue(){
        if(count() == 1) return rimozioneInTesta();
        
        
        /*Oppure:
        
        if(count() == 1){
            Nodo nodoDaRimuovere = head;
            head = null;
            return nodoDaRimuovere.getInvitato();
        }
        
        */
        
        
        p = head;
        s = head;

        while(s.getLink() != null){
            p = s;
            s = s.getLink();
        }

        Node nodoDaRimuovere = s;
        p.setLink(null);
        return nodoDaRimuovere.getInfo();
    }

    /*public Invitato remove(int posizione){
        if(posizione == 1) return rimozioneInTesta();
        if(posizione == count()) return dequeue();

        p = head;
        s = head.getLink().getLink();

        while(posizione - 2 > 0){ // presuppongo che le posizioni partano da 1
            posizione--;
            
            if(s == null) {
                System.out.println("Rimozione fallita");
                return null;
            }

            p = p.getLink();
            s = s.getLink();
        }

        Node nodoDaRimuovere = p.getLink();
        p.setLink(s);
        return nodoDaRimuovere.getInvitato();
    }*/
    
    public int count(){
        int numeroElementi = 0;
        Node node = head;
        
        while(node != null) {
            node = node.getLink();
            numeroElementi++;
        }
        
        return numeroElementi;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    @Override
    public String toString() {
        String s = "Lista:";

        if(head == null){
            s += "\nVuota";
            return s;
        }

        s += "\n\n";

        Node node = head;
        for (int i = 0; node != null; i++){
            s += "\n" + (i + 1) + ") " + node.toString() + "\n";
            node = node.getLink();
        }

        return s;
    }

}
