package modele;

public class Terre extends Case {

	public boolean estNavigable() {
		return false;
	}

	@Override
	//Ce n'est pas sensé arriver
	public boolean ajouterNavire(Navire n) {
		return false;
	}

	@Override
	//Ce n'est pas sensé arriver
	public boolean enleverNavire() {
		return false;
	}

}
