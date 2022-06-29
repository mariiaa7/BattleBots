package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import squadra.Squadra;

public class Dat {

	public Dat(String s)
	{
		nome = s;
	}
	
	public boolean exists()
	{
		File f = new File(nome);
		if(f.exists())
			return true;
		return false;
	}
	
	public void salva(Squadra s)
	{
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nome));
			out.writeObject(s);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Squadra leggi()
	{
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(nome));
			return (Squadra) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private String nome;
}
