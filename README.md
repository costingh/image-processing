# image-processing
Multithreaded Java Algorithm for decreasing color depth of a 24bit RGB BMP image.

# This application was developed for the Java course to implement Producer - Consumer problem using Threads.

The application gets an image as input and decreases its color depth (specified by the user).

What is color depth?
The color depth represents the number or bits used to store a color representation. The input image is a 24bit bmp grayscale image, therefore it has 8bits for each of this 3 colors: Red, Green, Blue, but because its grayscale, all values are equal (R=G=B).

The program uses varargs to get the image name, which is read by Producer thread as a byte stream, quarter by quarter. After this thread reads 1/4 of the image, it sends it to the Consumer thread to put this quarter in an array of bytes.
After the image is fully read as a byte array, it is converted to BufferdImage object to create a 2D array of pixels.
The processing algorithm iterates over this matrix of pixels and uses bitwise operations to decrease color depth.
At the end of processing the image is written to the file path specified by user.
