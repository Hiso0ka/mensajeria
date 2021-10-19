package my.hacha;



import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Optional;




public class FileManagement {
	
	public static void partitionFile(int numSplits,File fileSelected) throws IOException  {
		// TODO Auto-generated method stub	
		
		
			
        RandomAccessFile raf = new RandomAccessFile(fileSelected, "r");
        long sourceSize = raf.length(); //Devuelve la longitud del fichero
        long bytesPerSplit = sourceSize/numSplits; //Número de bytes por partición del archivo
        long remainingBytes = sourceSize % numSplits; //Número de Bytes restantes
        int maxReadBufferSize = 8 * 1024; //8KB Tamaño de cada bloque de lectura
        
        for(int bloqueLectura=1; bloqueLectura <= numSplits; bloqueLectura++) {//bloqueLectura es la variable que recorre el archivo.
            BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(arreglarRuta(fileSelected) + fileSelected.getName().substring(0, fileSelected.getName().lastIndexOf(".")) + "Parte"+bloqueLectura));
            if(bytesPerSplit > maxReadBufferSize) {
                long numReads = bytesPerSplit/maxReadBufferSize;
                long numRemainingRead = bytesPerSplit % maxReadBufferSize;
                for(int i=0; i<numReads; i++) {
                    readWrite(raf, bw, maxReadBufferSize);
                }
                if(numRemainingRead > 0) {
                    readWrite(raf, bw, numRemainingRead);
                }
            }else {
                readWrite(raf, bw, bytesPerSplit);
            }
            bw.close();
        }
        if(remainingBytes > 0) {
            BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(arreglarRuta(fileSelected) + fileSelected.getName().substring(0, fileSelected.getName().lastIndexOf(".")) + "Parte"+numSplits, true));
            readWrite(raf, bw, remainingBytes);
            bw.close();
        }
            raf.close();
	}

	private static void readWrite(RandomAccessFile raf, BufferedOutputStream bw, long numBytes) throws IOException {
		// TODO Auto-generated method stub
			byte[] buf = new byte[(int) numBytes];
	        int val = raf.read(buf);
	        if(val != -1) {
	        bw.write(buf);
	        }
	
	}
	
	public static String arreglarRuta(File fileSelected) {
			
		String pathFile;
		if (System.getProperty("os.name").toLowerCase().contains("win")) {
			pathFile = (fileSelected.getAbsolutePath().substring(0, fileSelected.getAbsolutePath().lastIndexOf("\\"))+"\\");
			
		} else {
			pathFile = (fileSelected.getAbsolutePath().substring(0, fileSelected.getAbsolutePath().lastIndexOf("/"))+"/");
		} return pathFile;		
	}
	

}