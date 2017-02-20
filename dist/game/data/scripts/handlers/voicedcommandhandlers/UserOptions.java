/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers.voicedcommandhandlers;

import com.l2jserver.gameserver.handler.IVoicedCommandHandler;
import com.l2jserver.gameserver.model.PcCondOverride;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;

/**
 * @author GKR, Mr.Deff
 */
public class UserOptions implements IVoicedCommandHandler
{
	private static final String[] _voicedCommands =
	{
		"expon",
		"expoff",
	};

	public boolean useVoicedCommand(String command, L2PcInstance activeChar, String target)
	{
		if (activeChar == null)
		{
			return false;
		}

		switch(command)
		{
			case "expoff":
				activeChar.addOverrideCond(PcCondOverride.DISABLE_EXP_GAIN);
				activeChar.sendMessage("Exp gain disabled.");
				break;
			case "expon":
				activeChar.removeOverridedCond(PcCondOverride.DISABLE_EXP_GAIN);
				activeChar.sendMessage("Exp gain restored.");
				break;
		}

		return true;
	}

	public String[] getVoicedCommandList()
	{
		return _voicedCommands;
	}
}