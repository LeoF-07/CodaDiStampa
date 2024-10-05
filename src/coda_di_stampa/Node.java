package coda_di_stampa;

import java.io.File;

public class Node {

    private String info;
    private Node link;

    private boolean linkStampato = false;

    public Node(String info, Node link) {
        setInfo(info);
        setLink(link);
    }

    public Node() {
        setInfo(null);
        setLink(null);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Node getLink() {
        return link;
    }

    public void setLink(Node link) {
        this.link = link;
    }

    public void setLinkStampato(boolean b){
        this.linkStampato = b;
    }

    @Override
    public String toString() {
        String s = "Nodo:\n";
        s += "Info: " + info.toString();

        if(!linkStampato) {
            s += "\nLink: ";

            if(link == null){
                s += "null";
                return s;
            }

            link.setLinkStampato(true);
            s += link.getInfo().toString();
            link.setLinkStampato(false);
        }

        return s;
    }
}
