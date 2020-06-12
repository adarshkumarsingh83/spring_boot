package com.espark.adarsh.test;

import com.espark.adarsh.ApplicationMain;
import com.espark.adarsh.entities.Permission;
import com.espark.adarsh.entities.User;
import com.espark.adarsh.service.UserService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationMain.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void testSaveUser() {
        final Permission permission = new Permission() {
            {
                setPermissionId(100);
                setPermissionName("ADMIN");
            }
        };
        final User user = new User() {
            {
                setUserId(100);
                setUserName("adarsh");
                setPermission(permission);
            }
        };
        Mockito.when(userService.saveUser(user)).thenReturn(Boolean.TRUE);
        boolean value=this.userService.saveUser(user);
        Assert.assertEquals(value,true);
    }

    @Test
    public void testGetUser() {
       final User user= new User(){
           {
               setUserId(100);
               setUserName("Adarsh");
               setPermission(new Permission(100,"admin"));
           }
       };
        Mockito.when(userService.getUser(100)).thenReturn(user);
       final User user1=this.userService.getUser(100);
        Assert.assertEquals(user1.getUserId(),user.getUserId());
    }


}
