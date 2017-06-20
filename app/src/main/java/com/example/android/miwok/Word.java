package com.example.android.miwok;

/**
 * Created by mesi on 17/06/17.
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a Miwok translation for that word.
 */

public class Word {
    private final static int NO_IMAGE_PROVIDED = -1;
    private final static int NO_ADUIO_PROVIDED = -1;

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private int mAudioResourceId = NO_ADUIO_PROVIDED;

    /**
     * Constructor
     *
     * @param mMiwokTranslation   Miwok word
     * @param mDefaultTranslation Default translation for the given Miwok word
     */
    public Word(String mMiwokTranslation, String mDefaultTranslation, int audioResourceId) {
        this.mMiwokTranslation = mMiwokTranslation;
        this.mDefaultTranslation = mDefaultTranslation;
        this.mAudioResourceId = audioResourceId;
    }

    /**
     * Constructor
     *
     * @param mMiwokTranslation   Miwok word
     * @param mDefaultTranslation Default translation for the given Miwok word
     */
    public Word(String mMiwokTranslation, String mDefaultTranslation, int imageResourceId, int audioResourceId) {
        this.mMiwokTranslation = mMiwokTranslation;
        this.mDefaultTranslation = mDefaultTranslation;
        this.mImageResourceId = imageResourceId;
        this.mAudioResourceId = audioResourceId;
    }

    /**
     * @return default translation of the word
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * @return Miwok translation word
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    /**
     * @return image resource id
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * @return audio resource id
     */
    public int getAudioResourceId() {
        return mAudioResourceId;
    }

    /**
     * Returns whether or not an image has been set for this word
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mAudioResourceId=" + mAudioResourceId +
                '}';
    }
}
