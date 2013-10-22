package com.chy.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.primefaces.model.UploadedFile;
import sun.misc.BASE64Encoder;

public class UploadUtil
{
  public static String getBase64(UploadedFile file)
  {
	  String base64 = null;
    InputStream in = null;
   
    try
    {
      in = file.getInputstream();
      byte[] bytes = new byte[in.available()];
      in.read(bytes);
      BASE64Encoder encoder = new BASE64Encoder();
      base64 = "data:" + file.getContentType() + ";base64," + encoder.encode(bytes);

      return base64;
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();

      return base64;
    }
    catch (IOException e)
    {
      e.printStackTrace();

      return base64;
    }
    finally
    {
      if (null != in)
        try {
          in.close();
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      return base64;
    }
    
  }
}