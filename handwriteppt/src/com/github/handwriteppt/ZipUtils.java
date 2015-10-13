package com.github.handwriteppt;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @since 1.0
 */
public class ZipUtils
{

  public static final String  EXT      = ".zip";
  private static final String BASE_DIR = "";

  private static final String PATH   = "/";
  private static final int    BUFFER = 1024;

  /**
   *
   *
   * @param srcFile
   * @throws Exception
   */
  public static void compress(File srcFile) throws Exception
  {
    String name = srcFile.getName();
    String basePath = srcFile.getParent();
    String destPath = basePath + name + EXT;
    compress(srcFile, destPath);
  }

  /**
   *
   *
   * @param srcFile
   * @param destPath
   * @throws Exception
   */
  public static void compress(File srcFile, File destFile) throws Exception
  {

    CheckedOutputStream cos = new CheckedOutputStream(new FileOutputStream(destFile), new CRC32());

    ZipOutputStream zos = new ZipOutputStream(cos);

    compress(srcFile, zos, BASE_DIR);

    zos.flush();
    zos.close();
  }

  /**
   *
   * @param srcFile
   * @param destPath
   * @throws Exception
   */
  public static void compress(File srcFile, String destPath) throws Exception
  {
    compress(srcFile, new File(destPath));
  }

  /**
   *
   * @param srcFile
   * @param zos
   *            ZipOutputStream
   * @param basePath
   * @throws Exception
   */
  private static void compress(File srcFile, ZipOutputStream zos, String basePath) throws Exception
  {
    if (srcFile.isDirectory())
    {
      compressDir(srcFile, zos, basePath);
    }
    else
    {
      compressFile(srcFile, zos, basePath);
    }
  }

  /**
   *
   * @param srcPath
   * @throws Exception
   */
  public static void compress(String srcPath) throws Exception
  {
    File srcFile = new File(srcPath);

    compress(srcFile);
  }

  /**
   *
   * @param srcPath
   * @param destPath
   *
   */
  public static void compress(String srcPath, String destPath) throws Exception
  {
    File srcFile = new File(srcPath);

    compress(srcFile, destPath);
  }

  /**
   *
   * @param dir
   * @param zos
   * @param basePath
   * @throws Exception
   */
  private static void compressDir(File dir, ZipOutputStream zos, String basePath) throws Exception
  {

    File[] files = dir.listFiles();

    if (files.length < 1)
    {
      ZipEntry entry = new ZipEntry(basePath + dir.getName() + PATH);

      zos.putNextEntry(entry);
      zos.closeEntry();
    }

    for (File file : files)
    {

      compress(file, zos, basePath + PATH);

    }
  }

  /**
   *
   * @param file
   * @param zos
   *            ZipOutputStream
   * @param dir
   * @throws Exception
   */
  private static void compressFile(File file, ZipOutputStream zos, String dir) throws Exception
  {

    ZipEntry entry = new ZipEntry(file.getName());

    zos.putNextEntry(entry);

    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

    int count;
    byte data[] = new byte[BUFFER];
    while ((count = bis.read(data, 0, BUFFER)) != -1)
    {
      zos.write(data, 0, count);
    }
    bis.close();

    zos.closeEntry();
  }

}
