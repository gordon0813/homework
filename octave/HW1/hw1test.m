clear all 
clc 

function [y] = lagrange(x, x0, y0) 

    n = size(x0,1); 
    y = 0; 

    for i=1:n 
     p = 1; 
     for j=1:n 

      if j == i % avoiding division by 0 
       continue; 
      endif; 

      p = p.*((x-x0(j))/(x0(i)-x0(j))); 

     endfor; 

     y += y0(i).* p; 
    endfor; 
endfunction; 

x=[0:0.1:5]; 

x0=[2;2.75;4]; 
y0=[1/2;1/2.75;1/4]; 

y=lagrange(x,x0,y0); 
plot(x,y)