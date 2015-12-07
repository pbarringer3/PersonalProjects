import random


def main():
    num_trials = 100000

    attacker_casualties = 0
    defender_casualties = 0

    for i in range(num_trials):
        roll = roll_dice(3, 2)
        attacker_casualties += roll[0]
        defender_casualties += roll[1]
    print("In a (3,2) split, attackers lose ", attacker_casualties/num_trials, " and defenders lose ", defender_casualties/num_trials)

    attacker_casualties = 0
    defender_casualties = 0

    for i in range(num_trials):
        roll = roll_dice(3, 1)
        attacker_casualties += roll[0]
        defender_casualties += roll[1]
    print("In a (3,1) split, attackers lose ", attacker_casualties/num_trials, " and defenders lose ", defender_casualties/num_trials)

    attacker_casualties = 0
    defender_casualties = 0

    for i in range(num_trials):
        roll = roll_dice(2,2)
        attacker_casualties += roll[0]
        defender_casualties += roll[1]
    print("In a (2,2) split, attackers lose ", attacker_casualties/num_trials, " and defenders lose ", defender_casualties/num_trials)

    attacker_casualties = 0
    defender_casualties = 0

    for i in range(num_trials):
        roll = roll_dice(2, 1)
        attacker_casualties += roll[0]
        defender_casualties += roll[1]
    print("In a (2,1) split, attackers lose ", attacker_casualties/num_trials, " and defenders lose ", defender_casualties/num_trials)

    attacker_casualties = 0
    defender_casualties = 0

    for i in range(num_trials):
        roll = roll_dice(1, 2)
        attacker_casualties += roll[0]
        defender_casualties += roll[1]
    print("In a (1,2) split, attackers lose ", attacker_casualties/num_trials, " and defenders lose ", defender_casualties/num_trials)

    attacker_casualties = 0
    defender_casualties = 0

    for i in range(num_trials):
        roll = roll_dice(1, 1)
        attacker_casualties += roll[0]
        defender_casualties += roll[1]
    print("In a (1,1) split, attackers lose ", attacker_casualties/num_trials, " and defenders lose ", defender_casualties/num_trials)



def roll_dice(num_attack_dice, num_defence_dice):
    attacker_casualties = 0
    defender_casualties = 0

    attack_dice = [random.randint(1,6) for i in range(num_attack_dice)]
    defence_dice = [random.randint(1,6) for i in range(num_defence_dice)]

    attack_dice.sort(reverse=True)
    defence_dice.sort(reverse=True)

    for pair in zip(attack_dice, defence_dice):
        if pair[0]>pair[1]:
            defender_casualties += 1
        else:
            attacker_casualties += 1

    return attacker_casualties, defender_casualties


if __name__ == "__main__":
    main()
