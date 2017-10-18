package tn.esprit.filters;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import tn.esprit.authorization.AllowTo;

@Provider
public class AccesDynamicFeature implements DynamicFeature  {
/* if u get an import error : 
	go to window > preferences > jax-RS validator > disable it*/
	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
		System.out.println("start dynamic");
	    if (resourceInfo.getResourceMethod().isAnnotationPresent(AllowTo.class)) {
	    	AllowTo annotation = resourceInfo.getResourceMethod().getAnnotation(AllowTo.class);
	    	  if(annotation!=null)
	    	  {
	    		  for(int i=0;i<annotation.roles().length;i++)
	    		  {System.out.println(annotation.roles()[i]);}
	    		  System.out.println("end roles");
	    	  }
	        context.register(new CostumFilter(annotation.roles()));}
	    System.out.println("end dynamic");
		}


}
