package com.minestellar.core.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.minecraft.util.ChatComponentText;

import com.minestellar.core.Version;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class ThreadVersionCheck extends Thread
{
    public static ThreadVersionCheck INSTANCE = new ThreadVersionCheck();
    private int count = 0;

    public static int remoteMajVer;
    public static int remoteMinVer;
    public static int remoteBuildVer;

    public ThreadVersionCheck()
    {
        super("Minestellar Version Check Thread");
    }

    public static void startCheck()
    {
        final Thread thread = new Thread(ThreadVersionCheck.INSTANCE);
        thread.start();
    }

    @Override
    public void run()
    {
        final Side sideToCheck = FMLCommonHandler.instance().getSide();

        if (sideToCheck == null)
        {
            return;
        }

        while (this.count < 3 && remoteBuildVer == 0)
        {
            try
            {
                final URL url = new URL("http://minestellar.host56.com/version.html"); // TODO: Change URL
                final HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.addRequestProperty("User-Agent", "Mozilla/4.76");
                final BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
                String str;
                String str2[] = null;

                while ((str = in.readLine()) != null)
                {

                    if (str.contains("Version"))
                    {
                        str = str.replace("Version=", "");

                        str2 = str.split("#");

                        if (str2.length == 3)
                        {
                            remoteMajVer = Integer.parseInt(str2[0]);
                            remoteMinVer = Integer.parseInt(str2[1]);
                            remoteBuildVer = Integer.parseInt(str2[2]);
                        }

                        if (remoteMajVer > Version.LOCALMAJVERSION || remoteMajVer == Version.LOCALMAJVERSION && remoteMinVer > Version.LOCALMINVERSION || remoteMajVer == Version.LOCALMAJVERSION && remoteMinVer == Version.LOCALMINVERSION && remoteBuildVer > Version.LOCALBUILDVERSION)
                        {
                            Thread.sleep(5000);

                            if (sideToCheck.equals(Side.CLIENT))
                            {
                                FMLClientHandler.instance().getClient().thePlayer.addChatMessage(new ChatComponentText(EnumColor.GREY + "New " + EnumColor.DARK_AQUA + "Minestellar" + EnumColor.GREY + " version available! v" + String.valueOf(remoteMajVer) + "." + String.valueOf(remoteMinVer) + "." + String.valueOf(remoteBuildVer) + EnumColor.DARK_BLUE + " http://minestellar.host56.com/"));
                            }
                            
                            else if (sideToCheck.equals(Side.SERVER))
                            {
                                MinestellarLog.severe("New Minestellar version available! v" + String.valueOf(remoteMajVer) + "." + String.valueOf(remoteMinVer) + "." + String.valueOf(remoteBuildVer) + " http://minestellar.host56.com/");
                            }
                        }
                    }
                }
            }
            
            catch (final Exception e)
            {
            }

            if (remoteBuildVer == 0)
            {
                try
                {
                    MinestellarLog.severe(MinestellarUtil.translate("update.failed.name"));
                    Thread.sleep(15000);
                }
                
                catch (final InterruptedException e)
                {
                }
            }
            
            else
            {
                MinestellarLog.info(MinestellarUtil.translate("update.success.name") + " " + remoteMajVer + "." + remoteMinVer + "." + remoteBuildVer);
            }

            this.count++;
        }
    }
}
