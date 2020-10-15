package com.timi.contoller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.timi.entities.Dept;
import com.timi.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptContoller {

    @Autowired
    private DeptService service = null;


    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept)
    {
        return this.service.add(dept);
    }


    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list()
    {
        return this.service.list();
    }

    @RequestMapping(value="/dept/get/{id}",method=RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public Dept get(@PathVariable("id") Long id)
    {
        Dept dept =  this.service.get(id);
        if(null == dept)
        {
            throw new RuntimeException("该ID："+id+"没有没有对应的信息");
        }
        return dept;
    }

    public Dept processHystrix_Get(@PathVariable("id") Long id)
    {
        return new Dept().setDname("该ID："+id+"没有没有对应的信息,null--@HystrixCommand")
                .setDb_source("no this database in MySQL");
    }

}
