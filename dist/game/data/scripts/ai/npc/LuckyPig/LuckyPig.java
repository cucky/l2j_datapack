/*
 * Copyright (C) 2004-2015 L2J DataPack
 * 
 * This file is part of L2J DataPack.
 * 
 * L2J DataPack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J DataPack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package ai.npc.LuckyPig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import ai.npc.AbstractNpcAI;

import com.l2jserver.gameserver.ai.CtrlIntention;
import com.l2jserver.gameserver.model.L2Object;
import com.l2jserver.gameserver.model.L2World;
import com.l2jserver.gameserver.model.Location;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.itemcontainer.Inventory;
import com.l2jserver.gameserver.model.items.instance.L2ItemInstance;
import com.l2jserver.gameserver.network.serverpackets.NpcSay;
import com.l2jserver.gameserver.util.Util;

/**
 * @author Shaiphodias
 */

public final class LuckyPig extends AbstractNpcAI
{
    // Lucky Pig IDs
    private final int Lucky_Pig = 2501;
    private final int Wingless_Lucky_Pig = 2502;
    private final int Golden_Wingless_Lucky_Pig = 2503;
    
    // Adena Map
    private final Map<Integer, List<Long>> Adena;
    
    private boolean isLuckyPigLevel52 = false;
    private boolean isLuckyPigLevel70 = false; 
    private boolean isLuckyPigLevel80 = false;

   // Lucky Pig Spawn Chance
   private final int Lucky_Pig_Level_52_Spawn_Chance = 3;
   private final int Lucky_Pig_Level_70_Spawn_Chance = 3;
   private final int Lucky_Pig_Level_80_Spawn_Chance = 3;

   // Wingless Lucky Pig Drop Chance
   private final int Wingless_Lucky_Pig_Level_52_Drop_Chance = 99;
   private final int Wingless_Lucky_Pig_Level_70_Drop_Chance = 99;
   private final int Wingless_Lucky_Pig_Level_80_Drop_Chance = 99;
   
   // Golden Wingless Lucky Pig Drop Chance
   private final int Golden_Wingless_Lucky_Pig_Level_52_Drop_Chance = 99;
   private final int Golden_Wingless_Lucky_Pig_Level_70_Drop_Chance = 99;
   private final int Golden_Wingless_Lucky_Pig_Level_80_Drop_Chance = 99;
    
    // Monsters IDs
    private final int Lucky_Pig_Level_52[] =
    {
        // Enchanted Valley
        20589,
        20590,
        20591,
        20592,
        20593,
        20594,
        20595,
        20596,
        20597,
        20598,
        20599
    };
    
    private final int Lucky_Pig_Level_70[] =
    {
        // Forest of the Dead
        18119,
        21555,
        21556,
        21547,
        21553,
        21548,
        21557,
        21559,
        21560,
        21561,
        21562,
        21563,
        21564,
        21565,
        21566,
        21568,
        21567,
        21596,
        21572,
        21573,
        21571,
        21570,
        21574,
        21576,
        21599,
        21580,
        21581,
        21579,
        21582,
        21578,
        21586,
        21587,
        21583,
        21585,
        21590,
        21593,
        21588,
        
        // Valley of Saints
        21520,
        21521,
        21524,
        21523,
        21526,
        21529,
        21541,
        21531,
        21530,
        21533,
        21532,
        21536,
        21535,
        21537,
        21539,
        21544
    };
    
    private final int Lucky_Pig_Level_80[] =
    {
        // Beast Farm
        18873,
        18880,
        18887,
        18894,
        18906,
        18907,
        18874,
        18875,
        18876,
        18877,
        18878,
        18879,
        18881,
        18882,
        18883,
        18884,
        18885,
        18886,
        18888,
        18889,
        18890,
        18891,
        18892,
        18893,
        18895,
        18896,
        18897,
        18898,
        18899,
        18900,
        
        // Plains of the Lizardmen
        22768,
        22769,
        22773,
        22772,
        22771,
        22770,
        22774,
        
        // Sel Mahum Training Grounds
        18908,
        22780,
        22782,
        22784,
        22781,
        22783,
        22785,
        22776,
        22786,
        22787,
        22788,
        22775,
        22777,
        22778,
        
        // Fields of Silence & Fields of Whispers
        22651,
        22654,
        22650,
        22655,
        22652,
        22658,
        22659,
        
        // Crypts of Disgrace
        22704,
        22703,
        22705,
        
        // Den of Evil
        22701,
        22691,
        22698,
        22695,
        22694,
        22696,
        22692,
        22693,
        22699,
        22698,
        22697,
        18807,
        22702,
        
        // Primeval Island
        22196,
        22197,
        22198,
        22218,
        22223,
        22203,
        22204,
        22205,
        22220,
        22225,
        22743,
        22745,
        22200,
        22201,
        22202,
        22219,
        22224,
        22742,
        22744,
        22199,
        22212,
        22213,
        22222,
        22211,
        22227,
        22208,
        22209,
        22210,
        22221,
        22226,
        22214,
        
        // Dragon Valley
        22815,
        22822,
        22823,
        22824,
        22862,
        22818,
        22819,
        22860,
        22829,
        22858,
        22830,
        22828,
        22827,
        22826,
        22861,
        22825
    };
    
    // Lucky Pig Droplist items IDs
    private final int Wingless_Lucky_Pig_Level_52_Drop_Id = 8755; // Top-Grade Life Stone - Level 52
    
    private final int Wingless_Lucky_Pig_Level_70_Drop_Id[] =
    {
        5577, // Red Soul Crystal - Stage 11
        5578, // Green Soul Crystal - Stage 11
        5579  // Blue Soul Crystal - Stage 11
    };
    
    private final int Wingless_Lucky_Pig_Level_80_Drop_Id[] =
    {
        9552, // Fire Crystal
        9553, // Water Crystal
        9554, // Earth Crystal
        9555, // Wind Crystal
        9556, // Dark Crystal
        9557  // Holy Crystal
    };
    
    @Override
    public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
    {
        if (event.equals("checkForAdena"))
        {
            try
            {
                for (L2Object object : L2World.getInstance().getVisibleObjects(npc, 1000))
                {
                    if (!(object instanceof L2ItemInstance))
                    {
                        continue;
                    }
                    
                    L2ItemInstance item = (L2ItemInstance) object;
                    
                    if (item.getId() == Inventory.ADENA_ID)
                    {
                        npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new Location(item.getX(), item.getY(), item.getZ(), 0));
                        L2World.getInstance().removeVisibleObject(item, item.getWorldRegion());
                        L2World.getInstance().removeObject(item);
                        
                        startQuestTimer("startTalking", 500, npc, null);
                        
                        if (Adena.containsKey(npc.getObjectId()))
                        {
                            Adena.get(npc.getObjectId()).add(item.getCount());
                            
                            int feedTimes = getRandom(10);
                            
                            if (Adena.get(npc.getObjectId()).size() > feedTimes)
                            {
                                long adenaCount = 0;
                                
                                for (long adena : Adena.get(npc.getObjectId()))
                                {
                                    adenaCount += adena;
                                }
                                
                                if (adenaCount >= 1)
                                {
                                    int x = npc.getX();
                                    int y = npc.getY();
                                    int z = npc.getZ();
                                    
                                    npc.deleteMe();
                                    
                                    if (isLuckyPigLevel52)
                                    {
                                        addSpawn(Wingless_Lucky_Pig, x, y, z, 0, true, 5 * 60 * 1000, true);
                                        setLuckyPigLevel52(true);
                                    }
                                    else if (isLuckyPigLevel70)
                                    {
                                        addSpawn(Wingless_Lucky_Pig, x, y, z, 0, true, 5 * 60 * 1000, true);
                                        setLuckyPigLevel70(true);
                                    }
                                    else if (isLuckyPigLevel80)
                                    {
                                        addSpawn(Wingless_Lucky_Pig, x, y, z, 0, true, 5 * 60 * 1000, true);
                                        setLuckyPigLevel80(true);
                                    }
                                }
                                else if (adenaCount >= 50000000)
                                {
                                    int x = npc.getX();
                                    int y = npc.getY();
                                    int z = npc.getZ();
                                    
                                    npc.deleteMe();
                                    
                                    if (isLuckyPigLevel52)
                                    {
                                        addSpawn(Golden_Wingless_Lucky_Pig, x, y, z, 0, true, 5 * 60 * 1000, true);
                                        setLuckyPigLevel52(true);
                                    }
                                    else if (isLuckyPigLevel70)
                                    {
                                        addSpawn(Golden_Wingless_Lucky_Pig, x, y, z, 0, true, 5 * 60 * 1000, true);
                                        setLuckyPigLevel70(true);
                                    }
                                    else if (isLuckyPigLevel80)
                                    {
                                        addSpawn(Golden_Wingless_Lucky_Pig, x, y, z, 0, true, 5 * 60 * 1000, true);
                                        setLuckyPigLevel80(true);
                                    }
                                }
                                
                                cancelQuestTimer("checkForAdena", npc, null);
                            }
                        }
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else if (event.equals("startTalking"))
        {
            if (getRandomBoolean())
            {
                npc.broadcastPacket(new NpcSay(npc.getObjectId(), 0, npc.getId(), "Yum-yum, yum-yum"));
            }
         else if (getRandomBoolean())
         {
            npc.broadcastPacket(new NpcSay(npc.getObjectId(), 0, npc.getId(), "My stomach is empty"));
         }
         else if (getRandomBoolean())
         {
            npc.broadcastPacket(new NpcSay(npc.getObjectId(), 0, npc.getId(), "I feel a little woozy"));
         }
         else if (getRandomBoolean())
         {
            npc.broadcastPacket(new NpcSay(npc.getObjectId(), 0, npc.getId(), "I'm still not full"));
         }
         else if (getRandomBoolean())
         {
            npc.broadcastPacket(new NpcSay(npc.getObjectId(), 0, npc.getId(), "Give me something to eat"));
         }
         else if (getRandomBoolean())
         {
            npc.broadcastPacket(new NpcSay(npc.getObjectId(), 0, npc.getId(), "I also need dessert"));
         }
            else
            {
                npc.broadcastPacket(new NpcSay(npc.getObjectId(), 0, npc.getId(), "I'm still hungry~"));
            }
            
            cancelQuestTimer("startTalking", npc, null);
        }
        else if (event.equals("despawnLuckyPig"))
        {
            if (npc.getId() == Lucky_Pig)
            {
                npc.deleteMe();
            }
            
            cancelQuestTimer("despawnLuckyPig", npc, null);
        }
        else if (event.equals("despawnWinglessLuckyPig"))
        {
            if (npc.getId() == Wingless_Lucky_Pig)
            {
                npc.deleteMe();
            }
            else if (npc.getId() == Golden_Wingless_Lucky_Pig)
            {
                npc.deleteMe();
            }
            
            cancelQuestTimer("despawnWinglessLuckyPig", npc, null);
        }
        
        return super.onAdvEvent(event, npc, player);
    }
    
    @Override
    public String onKill(L2Npc npc, L2PcInstance player, boolean isPet)
    {
        if (Util.contains(Lucky_Pig_Level_52, npc.getId()))
        {
            if (getRandom(0, 100) <= Lucky_Pig_Level_52_Spawn_Chance)
            {
                L2Npc luckyPig52 = addSpawn(Lucky_Pig, npc.getX() + 50, npc.getY() + 50, npc.getZ(), npc.getHeading(), true, 10 * 60 * 1000, true);
                onSpawn(luckyPig52);
                setLuckyPigLevel52(true);
            }
        }
        else if (Util.contains(Lucky_Pig_Level_70, npc.getId()))
        {
            if (getRandom(0, 100) <= Lucky_Pig_Level_70_Spawn_Chance)
            {
                L2Npc luckyPig70 = addSpawn(Lucky_Pig, npc.getX() + 50, npc.getY() + 50, npc.getZ(), npc.getHeading(), true, 10 * 60 * 1000, true);
                onSpawn(luckyPig70);
                setLuckyPigLevel70(true);
            }
        }
        else if (Util.contains(Lucky_Pig_Level_80, npc.getId()))
        {
            if (getRandom(0, 100) <= Lucky_Pig_Level_80_Spawn_Chance)
            {
                L2Npc luckyPig80 = addSpawn(Lucky_Pig, npc.getX() + 50, npc.getY() + 50, npc.getZ(), npc.getHeading(), true, 10 * 60 * 1000, true);
                onSpawn(luckyPig80);
                setLuckyPigLevel80(true);
            }
        }
        
        if ((npc.getId() == Wingless_Lucky_Pig) && isLuckyPigLevel52)
        {
            if (getRandom(0, 100) <= Wingless_Lucky_Pig_Level_52_Drop_Chance)
            {
                int randomQuantity = getRandom(2);
                npc.dropItem(player, Wingless_Lucky_Pig_Level_52_Drop_Id, randomQuantity);
                setLuckyPigLevel52(false);
            }
        }
        else if ((npc.getId() == Wingless_Lucky_Pig) && isLuckyPigLevel70)
        {
            if (getRandom(0, 100) <= Wingless_Lucky_Pig_Level_70_Drop_Chance)
            {
                Random rnd = new Random();
                int randomDrop = rnd.nextInt(Wingless_Lucky_Pig_Level_70_Drop_Id.length);
                int randomQuantity = getRandom(2);
                npc.dropItem(player, Wingless_Lucky_Pig_Level_70_Drop_Id[randomDrop], randomQuantity);
                setLuckyPigLevel70(false);
            }
        }
        else if ((npc.getId() == Wingless_Lucky_Pig) && isLuckyPigLevel80)
        {
            if (getRandom(0, 100) <= Wingless_Lucky_Pig_Level_80_Drop_Chance)
            {
                Random rnd = new Random();
                int randomDrop = rnd.nextInt(Wingless_Lucky_Pig_Level_80_Drop_Id.length);
                int randomQuantity = getRandom(2);
                npc.dropItem(player, Wingless_Lucky_Pig_Level_80_Drop_Id[randomDrop], randomQuantity);
                setLuckyPigLevel80(false);
            }
        }
        else if ((npc.getId() == Golden_Wingless_Lucky_Pig) && isLuckyPigLevel52)
        {
            if (getRandom(0, 100) <= Golden_Wingless_Lucky_Pig_Level_52_Drop_Chance)
            {
                npc.dropItem(player, 14678, 1); // Neolithic Crystal - B
                setLuckyPigLevel52(false);
            }
        }
        else if ((npc.getId() == Golden_Wingless_Lucky_Pig) && isLuckyPigLevel70)
        {
            if (getRandom(0, 100) <= Golden_Wingless_Lucky_Pig_Level_70_Drop_Chance)
            {
                npc.dropItem(player, 14679, 1); // Neolithic Crystal - A
                setLuckyPigLevel70(false);
            }
        }
        else if ((npc.getId() == Golden_Wingless_Lucky_Pig) && isLuckyPigLevel80)
        {
            if (getRandom(0, 100) <= Golden_Wingless_Lucky_Pig_Level_80_Drop_Chance)
            {
                npc.dropItem(player, 14680, 1); // Neolithic Crystal - S
                setLuckyPigLevel80(false);
            }
        }
        
        return super.onKill(npc, player, isPet);
    }
    
    @Override
    public String onSpawn(L2Npc npc)
    {
        switch (npc.getId())
        {
            case Lucky_Pig:
                List<Long> _Adena = new ArrayList<>();
                Adena.put(npc.getObjectId(), _Adena);
                startQuestTimer("checkForAdena", 1000, npc, null, true);
                npc.broadcastPacket(new NpcSay(npc.getObjectId(), 0, npc.getId(), "Now it's time to eat~"));
                startQuestTimer("despawnLuckyPig", 600000, npc, null);
                break;
            case Wingless_Lucky_Pig:
                startQuestTimer("despawnWinglessLuckyPig", 600000, npc, null);
                break;
            case Golden_Wingless_Lucky_Pig:
                startQuestTimer("despawnWinglessLuckyPig", 600000, npc, null);
                break;
        }
        
        return super.onSpawn(npc);
    }
    
    public void setLuckyPigLevel52(boolean luckyPigLevel52)
    {
        isLuckyPigLevel52 = luckyPigLevel52;
    }
    
    public boolean isLuckyPigLevel52()
    {
        return isLuckyPigLevel52;
    }
    
    public void setLuckyPigLevel70(boolean luckyPigLevel70)
    {
        isLuckyPigLevel70 = luckyPigLevel70;
    }
    
    public boolean isLuckyPigLevel70()
    {
        return isLuckyPigLevel70;
    }
    
    public void setLuckyPigLevel80(boolean luckyPigLevel80)
    {
        isLuckyPigLevel80 = luckyPigLevel80;
    }
    
    public boolean isLuckyPigLevel80()
    {
        return isLuckyPigLevel80;
    }
    
    public LuckyPig()
    {
        super(LuckyPig.class.getSimpleName(), "ai/npc");
        
        addKillId(Wingless_Lucky_Pig);
        addKillId(Golden_Wingless_Lucky_Pig);
        
        Adena = new HashMap<>();
        registerMobs(Lucky_Pig_Level_52);
        registerMobs(Lucky_Pig_Level_70);
        registerMobs(Lucky_Pig_Level_80);
    }
    
    public static void main(String[] args)
    {
        new LuckyPig();
    }
}