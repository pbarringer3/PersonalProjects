import matplotlib.pyplot as plt
from matplotlib.widgets import CheckButtons, Button

def do_nothing(arg):
    print("do nothing", arg)

plt.rc('text', usetex=True)
plt.rc('font', family='serif')

fig = plt.figure(figsize=(8,5))

ax = plt.axes([0,0,0,0] )
rax = plt.axes([0.1, 0.1, 0.1, 0.2] )
check = CheckButtons(rax, ('red', 'blue', 'green'), (1,0,1))
for r in check.rectangles:
    r.set_edgecolor(None)

fig.text(0.5, 0.5, r"$\displaystyle\sum_{n=1}^\infty\frac{-e^{i\pi}}{2^n}$!", 
                ha='center', va='center',fontsize=60)

axprev = plt.axes([0.7, 0.05, 0.1, 0.075])
axnext = plt.axes([0.81, 0.05, 0.1, 0.075])
bnext = Button(axnext, 'Next')
bnext.on_clicked(do_nothing('a'))
bprev = Button(axprev, 'Previous')
bprev.on_clicked(do_nothing('b'))

plt.show()