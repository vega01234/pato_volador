package ValidFiles;
import java.io.*;
import java.security.*;
import java.security.spec.*;

public class VerFir {

    String rutaClave;
    String rutaFirma;
    String rutaDoc;
    boolean verificar;

    public boolean VerFir(String unaRutaClave, String unaRutaFirma, String unaRutaDoc) {
        rutaClave = unaRutaClave;
        rutaFirma = unaRutaFirma;
        rutaDoc = unaRutaDoc;

        /* Verify a DSA signature */

        if (rutaClave.equals("null")) {
            System.out.println("Usage: VerSig publickeyfile signaturefile datafile");
            }
        else try{

            /* Primero se carga la clave a llave */

            FileInputStream keyfis = new FileInputStream(rutaClave);
            byte[] encKey = new byte[keyfis.available()];
            keyfis.read(encKey);

            keyfis.close();

            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);

            KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
            PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);

            /* Segundo se carga la Firma Digital */
            FileInputStream sigfis = new FileInputStream(rutaFirma);
            byte[] sigToVerify = new byte[sigfis.available()];
            sigfis.read(sigToVerify );

            sigfis.close();

            /* create a Signature object and initialize it with the public key */
            Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
            sig.initVerify(pubKey);

            /* Tercero se carga el Archivo a verificar*/

            FileInputStream datafis = new FileInputStream(rutaDoc);
            BufferedInputStream bufin = new BufferedInputStream(datafis);

            byte[] buffer = new byte[1024];
            int len;
            while (bufin.available() != 0) {
                len = bufin.read(buffer);
                sig.update(buffer, 0, len);
                };

            bufin.close();


            verificar = sig.verify(sigToVerify);

            //System.out.println("signature verifies: " + verificar);


        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
                               };
    return verificar;

    }

}