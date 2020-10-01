
clear all
clc
list=textread("hw1cd.txt")
x=[-1:0.02:1]
ytotal=0


for i=1:11
  p=1
  for j=1:11
    if i==j
      continue
    endif
    
    
    p=p.*(x-list(j*2-1))/(list(i*2-1)-list(j*2-1))
    
  endfor
  
  p=p.*list(i*2)
  
  ytotal=ytotal+p
  figure(i)
  plot(x,p)
  title(["Largrange",num2str(i)])
  print("-djpeg",["picturec_",num2str(i),'.jpg'])
endfor
figure(12)
plot(x,ytotal)
print("-djpeg",["picturec_final",'.jpg'])