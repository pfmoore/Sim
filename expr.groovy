
def roll() {
    rng.nextInt(6) + 1
}

def dice(n, m) {
    result = []
    while (n--) {
        result.add(rng.nextInt(m) + 1)
    }
    result
}

def dnd() {
    l = [roll(), roll(), roll(), roll(), roll()]
    l.sort()
    l[2] + l[3] + l[4]
}

def ysphan() {
    l = trial.dice(9, 6)
    // l.unique().size()
    HashSet r = new HashSet();
    r.addAll(l)
    r.size()
}

ysphan()