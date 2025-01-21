[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/KprAwj1n)
# APCS - Stuyrim

## Features

Make a clear list of features that work/dont work

:white_check_mark: This feature works.

:question: This feature works partially.

:ballot_box_with_check: This extra (beyond the things that the lab was supposed to do) feature works.

:x: This required feature does not work.

:beetle: This is a bug that affects the game.


## Adventurer Subclasses


- 1/10 Created Blastoise and Charizard classes and pushed to repository.
- 1/10 Added Venasaur class to repository.
- 1/12 Finished movesets (implementation) for both Blastoise and Charizard classes.
- 1/13 Worked on terminal, created border and generate random enemies.
- 1/14 Changed Boss class and continued working on terminal.
- 1/15 Added turns (takes input but does not work as intended)
- 1/16 and 1/17 got turn prompts/answers to appear (Does not update the HP/stats)
- 1/18 Screen updates as intended, no death/method of winning.
- 1/19 added a method to win
- 1/20 Fixed bugs, added balancing, fixed some impossible to implement features.

Gameplay notes:
- A character cannot heal above max hp.
- Base Power(BP) is the maximum possible damage dealt. Every attack has a random multiplier of 0.85-1x, allowing for randomization to occur.
- A supporting move that increases damage dealt is always percentage based.
- SP, or special power, is restored everytime a non-special move is used. There is usually a maximum, as displayed below.
- You can choose who to use an action on by doing "attack/support #" (e.g. a 1, support 2, etc.) You cannot choose nothing by doing something such as "a_" or "a_6", as 6 would be out of bounds (The game will break if "a_" or "su_" is called :question:).



## Charizard
-250 HP
-200 Max SP
|Move Type|Name|Effect|
|---|---|---|
|Special|Blast Burn|Has 150 BP; uses 60 SP :white_check_mark: |
|Attack|Flamethrower|Has 90 BP; restores 30SP :white_check_mark: |
|Support|Helping Hand|Increases damage dealt by an ally by 100%; restores 10SP :white_check_mark: |
|Self-support|Roost|Heals 125HP; restores 20SP :white_check_mark: |

## Blastoise
-300 HP
-200 Max SP
|Move Type|Name|Effect|
|---|---|---|
|Special|Water Spout|Has 150 BP; uses 60 SP :white_check_mark: |
|Attack|Hydro Pump|Has 110 BP; restores 20SP :white_check_mark: |
|Support|Aqua Ring|Heals an ally for 100HP; restores 60SP :white_check_mark: |
|Self-support|Shell Smash|Increases attack damage by 100% and halves its current HP; restores 20SP :white_check_mark: |

## Venusaur
-350 HP
-200 Max SP
|Move Type|Name|Effect|
|---|---|---|
|Special|Solar Beam|Has 140 BP; uses 60 SP :white_check_mark: |
|Attack|Giga Drain|Has 70 BP, and restores the damage dealt back to Venusaur; restores 20SP :white_check_mark: |
|Support|Synthesis|Restores 175HP to an ally; restores 10SP :white_check_mark: |
|Self-support|Sunny Day|Increases attack damage by 100%; restores 10SP :white_check_mark: |

## Boss(Mewtwo)
-1000 HP
-200 Max SP
|Move Type|Name|Effect|
|---|---|---|
|Special|Psystrike|Has 280 BP; uses 60SP :white_check_mark: |
|Attack|Aura Sphere|Has 140 BP; restores 60SP :white_check_mark: |
|Support|Recover|Doubles current HP of an ally; restores 30SP :white_check_mark:  |
|Self-support|Amnesia|Doubles current HP; restores 30SP :white_check_mark: |


