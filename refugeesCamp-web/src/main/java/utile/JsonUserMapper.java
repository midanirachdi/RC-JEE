package utile;

import tn.esprit.entities.User;
import tn.esprit.utile.UserFactory;

public class JsonUserMapper {
	
	public static String Convert(String json)
	{
		String [] attrs=json.split(",");
		attrs[0].replace("{", "");
		attrs[attrs.length-1].replace("}", "");
		User u;
		boolean ok=false;
		int i=0;
		String aux;
		do
		{
			aux=attrs[i];
			if(aux.contains("role"))
			{
				ok=true;
				String[] auxTable=aux.split(":");
				u=UserFactory.getInstance(auxTable[1].trim());
			    attrs[i]="";
			}
		}while(!ok || i<attrs.length);
		
		String jsnString="{";
		for(int j=0;i<attrs.length;i++)
		{if(attrs[j]!="")
		 {
			jsnString=jsnString + attrs[j]+",";
		 }
		}
		
		jsnString=jsnString+"}";
		return jsnString;
	}
}
