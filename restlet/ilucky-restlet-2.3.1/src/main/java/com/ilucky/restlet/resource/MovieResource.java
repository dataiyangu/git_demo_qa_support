package com.ilucky.restlet.resource;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

public class MovieResource extends ServerResource{  
    
    @Get  
    public String play(){  
        return "电影正在播放...";  
    }  
      
    @Post  
    public String pause(){  
        return "电影暂停...";  
    }  
      
    @Put  
    public String upload(){  
        return "电影正在上传...";  
    }  
      
    @Delete  
    public String deleteMovie(){  
        return "删除电影...";  
    }  
    
    @Post  
    public String pause(String movie){  
        return "电影暂停..." + movie;  
    }  
    
    /*@Post  
    @Path("{id}/movie")
    public String pause(@PathParam("id") int id){  
        return "电影暂停..." + movie;  
    }  */
}  
