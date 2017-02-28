package handlers.voicedcommandhandlers;

import com.l2jserver.gameserver.handler.IVoicedCommandHandler;
import com.l2jserver.gameserver.model.L2World;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;

public class Online implements IVoicedCommandHandler
{
	private static String[] _voicedCommands =
	{
		"online"
	};

	@Override
	public boolean useVoicedCommand(String command, L2PcInstance activeChar, String target)
	{
		if(command.equalsIgnoreCase("online"))
		{
			activeChar.sendMessage("======<Status Online>======");
			activeChar.sendMessage("Players Online: " + L2World.getInstance().getAllPlayersCount());
			activeChar.sendMessage("GMs Online: " + L2World.getInstance().getAllGMs().size() + "");
			activeChar.sendMessage("=======================");
		}
		return true;
	}

	@Override
	public String[] getVoicedCommandList()
	{
		return _voicedCommands;
	}
}