# Competition

Being a programmer at an FRC competition can have you go from being very bored to very stressed quickly. There are many responsibilities the sub team as a whole has when it comes to competition, from the supplies we have to bring and supervise to the knowledge of our own code we are expected to have. This article will go ever what is likely to be expected of you during a competition. 


## Pre-Competition 

Before going to a competiton there is some work that has to be done before hand. Apart from work specifically regarding robot functionality, the team must make sure everything is updated. This includes (but not limited to) the driver station software, the roborio, the talons, and sensors. The router will have to be reconfigured at the competiton itself. 

Also, during a competition, you may be in a situation where you have to edit or explain your code quickly, which is why it is extremely important to clean it up before hand and **comment everything**. Cleaning up code involves making sure variables have useful names, everything is tabbed properly, there aren't unnecessary print statements, repetitive code is put into methods, etc. As for comments, every method and every class should be java doc-ed, and there should be helpful comments written within long methods to explain what's going on. Everyone on the sub team should be able to understand what each part of the code does just by reading comments. You may even want to try bringing in a non-programmer from a different sub team and explain the code using your comments. 

Test every functionality of the robot. Make sure every movement and routine functions properly before going into the competiton. While you do this, create a procedure and a list of system checks that must be done before every single match in the competition. Coordinate this list with the other sub teams as well, everyone should be aware of what has to be done to the robot before matches and every sub team should have input into this list. 

**Suggestionn for Technician and/or Programming leaders (but good advice for everyone):** Familiarize yourself with the code. Spend an hour sometime before the competition reading through everything. Try to imagine scenarios where something breaks or has to be changed and what code changes you would have to make. The better understanding you have of everything the team has written, the more prepared you will be for the competition. 

## Supplies

Certain supplies the programmers have to bring may be added based on the game and robot of that particular year, but here is a general checklist of what we should bring.

* Ethernet Cord
* Back Up Ethernet Cord
* USB A to USB B Cord
* Driver Station **(Fully Updated)**
* Back Up Driver Station **(Fully Updated)**
* Controllers
* Back Up Controllers
* MacBook Dongles **(Bring at least 3)**
* Vision Targets (Optional)

## During Competiton

It is very likely that you recieved barely any time whatsoever to write good auto paths, thankfully, competitions normally provide practice fields. Even if you were able to write and test paths, you should always double check them at the competition itself. Read the article about Autonomous for info on writing auto paths. If you are writing paths at the competition you will probably not have time to measure everything out, and will be guessing and checking distances or movements. A good strategy for doing this efficiently is to have one person on the laptop programming and another shouting if the distances have to be increased or decreased. For example, if you set the robot to drive 8 feet, the programmer would shout "Robot going 8 feet", then when the robot does that, it may be 1 foot away from where it needs to be to score, the other person would shout "Try increasing by 1 foot, set distance to 9", and the programmer would confirm "Robot going 9 feet", and repeat until you are satisfied. 

Ideally, the code the team writes for robot functionality would not have to be adjusted at all during competition. Unfortunately, this is not always the case. The most often kind of code change will probably occur with the drive team not happy with the speed of a certain mechanism. This change should just require changing values in the Constants class and nothing else. If a major change is required, before you actually make the change, take a second to remind yourself not to panic. You may feel like you need to rush, but if possible take a moment to access how much time you have and think through what needs to be done. You can always ask a fellow student or Mr. Kiddy for help if a task is to complicated or you are confused. You can even reach out to other teams if need be. 

**Always have and use a system check.** A system check should be done before every match, whenever a code change is done, or whenever a mechanical change is done. Once, in the 2018 game at a competition in Pittsburgh, our intake mechanism broke and we had to replace it, it was the same mechanism so no code changes had to be made and in our rush to go to our match, no one thought to test it. The replacement mechanism was wired in reverse from the original mechanism and therefore when the drivers tried to use it, it would do the opposite of what they expected. This rendered the mechanism useless because there was additional code that did things based on the state of the mechanism, and now those things were running at the wrong time. This could have been avoided if we had done a quick system check. 

### Helping other teams

In the spirit of coopertition, our team always helps others whenever asked. In terms of programming, we've helped teams by programming a sensor to writing their whole code from scratch. Any programmer on the subteam can help others, it does not have to be the captains and/or technician. When helping teams with their code, keep in mind that not every team uses the same organization as us, and you will need to adapt how you write the code. Some teams write everything in on class and some may be even more advanced than us. Also remember, the team you are helping is the one in charge, if you have an idea but they don't want to do it, then listen to them. (Of course, there is some leeway with this rule, once when helping a team write an auto path, while troubleshooting, a member of the other team insisted that it is impossible to concatenate a string and an int in java).

It is always a good idea to check in with teams you will be on an alliance with to make sure that they have an auto path that won't interfere with yours. If their path will interfere, then its up to you to make a judgement call on whether you want them to write another one (with or without your help) or if you should write another one or just run different paths altogether. Also, if a team you are going to play with has no auto paths and you have some free time, then you should ask if you can write one for them. Try to get an auto path that at least does the bare minimum to get points, normally this is just moving off a line. If you have more time and resources, consider writing another path that scores more points for them. 

It is especially important that your team mates on your alliance for the elimination rounds have auto paths that complements one of yours.