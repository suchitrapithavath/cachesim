import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.Scanner;

class cachemain
{
   static final List<Integer> cacheblocksizevalues = Arrays.asList(2,4,8,16,32,64);
   static final List<Integer> Assocvalues = Arrays.asList(0,1,2,4,8,16);
   static final List<Integer> victimvalues =Arrays.asList(4,8,16); 
   private static boolean victimCacheEnabled;
   public static void main (String args[]) throws IOException
   {
        
       try {
                 
        String fileflag =args[0];
        String filename=args[1];
        String cachesizeflag=args[2];
    	int cachesize=Integer.parseInt(args[3]);
        String cacheblocksizeflag=args[4];
    	int cacheblocksize=Integer.parseInt(args[5]);
        String assocflag =args[6];
        int Assoc=Integer.parseInt(args[7]);
        int victim=0;
        String victimflag;
        if(args.length > 8)
        {
         victimCacheEnabled =true;
         victim=Integer.parseInt(args[args.length-1]);
         victimflag=args[args.length-2];
        }
         if(args[2].equals("-cs")&&cachesize <=1024 && cachesize >=4194304)
         {
           System.out.println("Invalid cache size it should be less than 4194304");
           System.exit(0);
         }
       else if(args[4].equals("-bs") && !(cacheblocksizevalues.contains(cacheblocksize)))
           {
              System.out.println("Invalid cache block size it should be in {2,4,8,16,32,64}");
              System.exit(0);
            }

       else if(args[6].equals("-w")&& !(Assocvalues.contains(Assoc)))
            {
               System.out.println("Invalid Associative values,it should be in {0,1,2,4,8,16}");
               System.exit(0);
            }
        else if(args[args.length-2].equals("-vs") && !(victimvalues.contains(victim)))
             {
                System.out.println("Invalid Victim values,it should be in {4,8,16}");
                System.exit(0);
             }
        Cache cache;
        if(victimCacheEnabled) cache = new Cache(cachesize, cacheblocksize,Assoc,victim);
         else cache = new Cache(cachesize,cacheblocksize,Assoc);
           cache.memorytrace(filename,Assoc,cacheblocksize);
                System.out.println("file name" + filename);
                System.out.println("Cache size is: " + cachesize);
                System.out.println("Associtivity is"+Assoc);

           cache.results();
          
      }


    catch(Exception e)
  {
       System.out.println(e);
  }

       
}

}
