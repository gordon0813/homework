clear all 
clc
shape=15
matc=zeros(shape,shape)
matans=zeros(shape,shape)
matc(1,1)=1
matc(1,2)=2
for i=2:shape-1
  for j=1:shape
    if(j==i-1)
      matc(i,j)=1
    elseif(j==i)
      matc(i,j)=4
    elseif(j==i+1)
      matc(i,j)=1
    endif 
  end
end
matc(shape,shape-1)=2
matc(shape,shape)=1
matans(1,1)=-5/2
matans(1,2)=2
matans(1,3)=1/2
for i =2:shape-1
  for j=1:shape
    if(j==i+1)
      matans(i,j)=3
    elseif(j==i-1)
      matans(i,j)=-3
    endif
  end
end

matans(shape,shape)=5/2
matans(shape,shape-1)=-2
matans(shape,shape-2)=-1/2

x=linspace(0,3,shape)
x=x(1:shape)
mat_sin=linspace(0,3,shape)
mat_sin=mat_sin(1:shape)
mat_sin=sin(5*mat_sin)
matans=mat_sin.*matans
mat_ans=matc\matans
mat_sum=sum(mat_ans,2)/(x(2)-x(1))
x_real=[0:0.01:3]
mat_realsin=5*cos(5*x_real)
plot(x,mat_sum)
hold
plot(x_real,mat_realsin)
print("-djpeg",["pictured_C",'.jpg'])