import os

dir = os.getcwd()

filename = "write-stars"
filepath = os.path.join(dir, filename)

with open(filepath, 'w') as f:
    for row in range(10):
        for col in range(10):
            if row == col:
                f.write("*\n")
                break
            f.write("* ")

filename = "write-sequences"
filepath = os.path.join(dir, filename)

output = ["Don't open\n", "the chest!"]
with open(filepath, 'w') as f:
    f.writelines(output)

filename = "write-math"
filepath = os.path.join(dir, filename)

x = 3
y = 7
z = (x + y) / 2

with open(filepath, 'w') as f:
    f.write(str(z))