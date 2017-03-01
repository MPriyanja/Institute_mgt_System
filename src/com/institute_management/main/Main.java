
package com.institute_management.main;

import com.institute_management.user_mgt.UI.Login;
import com.institute_management.user_mgt.UI.privilegeAssign;
import java.util.ArrayList;
import javax.swing.UIManager;


public class Main {
    
    
    
    public static void main(String[] args) throws Exception {
       
     UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
     Login login = new Login();
     login.setVisible(true);
     
     
    }   

    
}
/*
TextureLookAndFeel – “com.jtattoo.plaf.texture.TextureLookAndFeel”
SmartLookAndFeel – “com.jtattoo.plaf.smart.SmartLookAndFeel”
NoireLookAndFeel – “com.jtattoo.plaf.noire.NoireLookAndFeel”
AcrylLookAndFeel – “com.jtattoo.plaf.acryl.AcrylLookAndFeel”
AeroLookAndFeel – “com.jtattoo.plaf.aero.AeroLookAndFeel”
AluminiumLookAndFeel – “com.jtattoo.plaf.aluminium.AluminiumLookAndFeel”
BernsteinLookAndFeel – “com.jtattoo.plaf.bernstein.BernsteinLookAndFeel”
FastLookAndFeel – “com.jtattoo.plaf.fast.FastLookAndFeel”
GraphiteLookAndFeel – “com.jtattoo.plaf.graphite.GraphiteLookAndFeel”
HiFiLookAndFeel – “com.jtattoo.plaf.hifi.HiFiLookAndFeel”
LunaLookAndFeel – “com.jtattoo.plaf.luna.LunaLookAndFeel”
McWinLookAndFeel – “com.jtattoo.plaf.mcwin.McWinLookAndFeel”
MintLookAndFeel – “com.jtattoo.plaf.mint.MintLookAndFeel”
*/