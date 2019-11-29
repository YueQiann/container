cmake_minimum_required(VERSION 3.4.1)

add_library(FileDescriptorPath SHARED
            com_qianyue_common_FileDescriptorPath.c)
target_link_libraries(FileDescriptorPath
                      android
                      log)