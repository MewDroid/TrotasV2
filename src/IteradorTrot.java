
public class IteradorTrot {

	private static final int FACTOR = 3;
	private static final int BASE_SIZE = 10;
	
	private Trot[] trts;
	private int count;
	
	public IteradorTrot() {
		trts = new Trot[BASE_SIZE];
		count = 0;
	}
	
	public Trot getTrot(int index) {
		return trts[index];
	}
	
	public boolean hasNext() {
		return count < trts.length;
	}
	
	private void resize() {
		if (count == trts.length) {
			Trot[] array2 = new Trot[FACTOR * trts.length];
			for (int i = 0; i < trts.length; i++) {
				array2[i] = trts[i];
			}
			trts = array2;
		}
	}
	
	public void append(Trot trot) {
		resize();
		trts[count] = trot;
		count++;
	}

	public int searchTrot(String idTrot) {
        int i;
        int trt = -1;
        for (i = 0; i < count; i++) {
            if (trts[i].getIdTrot().equalsIgnoreCase(idTrot)) {
                trt = i;
                break;
            }
        }
        return trt;
    }
	
//	public int searchTrotWithCoords(String idTrot) {
//		int i;
//		int trt = -1;
//		for (int i = 0; i < count; i++) {
//			if (trts[i].getCoordsTrot() != null) {
//				trt = i;
//				break;
//			}
//		}
//		return trt;
//	}
	
	
	
	public boolean hasTrot(String nif) {
	        int i;
	        boolean value = false;
	        for(i = 0; i < count; i++) {
	            if (trts[i].getIdTrot().equalsIgnoreCase(nif)) {
	            	value = true;
	            }
	        }
	        return value;
	    }

	public Cliente getCliente(String idTrot) {
		return trts[searchTrot(idTrot)].getUtilizador();
	}
	
	public Cliente getUtilizador(String idTrot) {
		return trts[searchTrot(idTrot)].getUtilizador();
	}
	
	public String getMatricula(String idTrot) {
		return trts[searchTrot(idTrot)].getMatricula();
	}

	public void setInativa(String idTrot, boolean i) {
		trts[searchTrot(idTrot)].setInativa(i);
	}
	
	public void setCoords(String idTrot, double xCord, double yCord) {
		trts[searchTrot(idTrot)].setCoords(xCord, yCord);
	}
	
	public void setCliente(String idTrot, Cliente cliente) {
		trts[searchTrot(idTrot)].setCliente(cliente);
	}
	
	public boolean isInativa(String idTrot) {
		return trts[searchTrot(idTrot)].isInativa();
	}
	
	public boolean livre(String idTrot) {
		return trts[searchTrot(idTrot)].livre();
	}
	
	public boolean withCoordsExists(String idTrot) {
		return trts[searchTrot(idTrot)].withCoordsExist();
	}
	
	public void incrementarAlugueres(String idTrot) {
		trts[searchTrot(idTrot)].incrementarAlugueres();
	}
	
	public void mudarTotalMinutos(String idTrot, int amount) {
		trts[searchTrot(idTrot)].mudarTotalMinutos(amount);
	}
	
	public String estado(String idTrot) {
		return trts[searchTrot(idTrot)].estado();
	}
	
	public int getAlugueres(String idTrot) {
		return trts[searchTrot(idTrot)].getAlugueres();
	}
	
	public int getTotalMinutos(String idTrot) {
		return trts[searchTrot(idTrot)].getTotalMinutos();
	}

	public int getCount() {
		return count;
	}

	public String getId(int i) {
		return trts[i].getIdTrot();
	}
/*
	public boolean hasTrotWithCoords() {
		boolean r = false;
		for (int i = 0; i< count; i++) {
			if (trts[i].withCoordsExist()) {
				r = true;
				break;
			}
		}
		return r;
	}
*/	
	public Trot closest(double longitude, double latitude) {
		double dist = -1;
		Trot trt = null;
		for (int i = 0; i<count; i++) {
			double t = trts[i].getDistanceTo(longitude, latitude);
			if (t> dist) {
				dist = t;
				trt = trts[i];
			}
		}
		return trt;
	}
	
}
