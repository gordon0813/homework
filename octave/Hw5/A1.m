clear all 
clc
t=linspace(0,2*pi);
root = [];
for j = 1:100
  p=[1 2 2-2*exp(i*t(j))];
  root = [root,roots(p)];
end
plot(root,"x");
hold