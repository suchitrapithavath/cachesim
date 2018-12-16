import java.io.IOException;
import java.util.*;
import java.math.BigInteger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Cache
{
     int num_blocks,index_bits,offset_bits,sets,tag_bits;
     private long hits, miss, access;
     double hitrate,missrate;
     ArrayList<List<String>> contents; 
     private  Victimcache victCache;
     public Cache(int cache_size,int cache_blocksize,int Assoc_way)
     {
         num_blocks=cache_size/cache_blocksize;
         if(Assoc_way ==0)
         {
           sets=1;
           Assoc_way =num_blocks;
          
         }
        else
       {
                sets=num_blocks/Assoc_way;
       }
           contents = new ArrayList<List<String>>(sets);
		for(int i = 0; i < sets; i++) {
			contents.add(new ArrayList<String>(Assoc_way));
		}
           hits=0;miss=0;access=0;
         victCache=null;
     }

   public Cache(int cacheSize, int blockSize, int associativeNum, int victimCacheSize) {

		this(cacheSize, blockSize, associativeNum);

		victCache = new Victimcache(victimCacheSize);

	}

   public void memorytrace(String filename,int assoc,int cache_blocksize) throws IOException {
    	BufferedReader br = new BufferedReader(new FileReader(filename));
    	String address;
            	while ((address = br.readLine()) != null) 
    	{
    		 access++;
                 String[] tokens=address.split(" ");
    		 String m_instuction=tokens[0];
    		 String m_offset=tokens[1];
    		 String m_address=tokens[2];
                 BigInteger addr = new BigInteger(m_address, 16);
	         BigInteger offset = new BigInteger(Integer.toString(Integer.parseInt(m_offset)));
		 addr = addr.add(offset);
		 m_address = addr.toString(2);
    		 String m_pc;
    		 int count;
    		 int length;
    		 if(m_address.length()>32)
    			 {
    			 length=m_address.length()-32;
    		     m_pc=m_address.substring(length);
    			 }
    		 else
    		     {
    			 length=32-m_address.length();
    			 String append="";
    				for(count=0;count<length;count++)
    				{
    					append=append+"0";
    				}
    			 m_pc=append+m_address.substring(0);
    		     }
                        offset_bits = (int) (Math.log(cache_blocksize)/Math.log(2));
			index_bits = (int) (Math.log(sets)/Math.log(2));
			tag_bits = 32 - offset_bits - index_bits;
			String tag = m_pc.substring(0, tag_bits);
                        int value =index_bits+tag_bits;
			String index = m_pc.substring(tag_bits,value);
                        int set_num;
    		            if(index.equals(""))
    		              set_num=0;
    		            else
    	                   set_num=Integer.parseInt(index, 2);
                          checkTag(tag,set_num,assoc,(victCache!= null));
                        
                     hitrate = (double) hits / access;
		     missrate = 1 - hitrate;
                         
                 
                     }
                    br.close();

}
private void checkTag(String tag, int setNum,int assoc,boolean useVictimCache) {
                String temp="";
                 List<String> list = contents.get(setNum);
		if(useVictimCache) {

			int foundTagInCache = findTag(setNum, tag);

			if(foundTagInCache != -1) {

				list.remove(foundTagInCache);

				list.add(tag);

				hits++;

			} else { 

				int foundTagInVictim = victCache.findtag(tag);

				if(foundTagInVictim != -1) {

					

					if(!list.isEmpty()) {

						 temp = list.remove(0);

					}

					list.add(tag);

					victCache.removetag(foundTagInVictim);

					victCache.addtovCache(temp);

					hits++;

				} else { 

				

					if(list.size() == assoc) {

						victCache.addtovCache(tag);

					}

					list.add(tag);

					miss++;

				}

			}

		} 

else {
			int foundTag = findTag(setNum, tag);
			if(foundTag != -1) {
				list.remove(foundTag);
				hits++;
			} else { 
				if(!(list.isEmpty())&&list.size() == assoc) {
					
					list.remove(0);
				}
				miss++;
			}
			list.add(tag);
               
}

}

private int findTag(int set_Num, String tag) {
		List<String> list = contents.get(set_Num);
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).equals(tag)) return i;
		}
		return -1;
	}


public void results() {
                System.out.println("Number of hits is: " + hits);
                System.out.println("Number of miss is: " + miss);
                System.out.println("Total memory accesses is: " + access);
                System.out.println("Hit rate is: " + hitrate);
                System.out.println("Miss rate is: " + missrate);

		System.out.println("Number of sets is: " + sets);
		System.out.println("Number of offset bits is: " + offset_bits);
		System.out.println("Number of index bits is: " + index_bits);
		System.out.println("Number of tag bits is: " + tag_bits);
	}
}
